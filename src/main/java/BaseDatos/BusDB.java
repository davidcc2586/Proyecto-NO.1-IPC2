package BaseDatos;

import Objetos.Bus;
import Objetos.Enums.Estado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusDB extends DB{

    public List<Bus> busesSucursal(int idSucursal) throws SQLException {
        crearConexion();
        List<Bus> buses = new ArrayList<>();
        String sqlSolicitarBuses = "SELECT * FROM Bus WHERE id_sucursal = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitarBuses);
        preparedStatement.setInt(1,idSucursal);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Bus bus = crearBus(resultSet);
            buses.add(bus);
        }
        cerrarConexion();
        return buses;
    }

    public Optional<Bus> obtenerBus (int idBus) throws SQLException {
        crearConexion();
        Optional<Bus> bus = Optional.empty();
        String sqlSolicitarBuses = "SELECT * FROM Bus WHERE id_bus = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitarBuses);
        preparedStatement.setInt(1,idBus);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            bus = Optional.of(crearBus(resultSet));
        }
        cerrarConexion();
        return bus;
    }

    private Bus crearBus(ResultSet resultSet) throws SQLException {
        int id_bus = resultSet.getInt("id_bus");
        String imagen = resultSet.getString("imagen");
        String numeroPlaca = resultSet.getString("numeroPlaca");
        String marca = resultSet.getString("marca");
        String modelo = resultSet.getString("modelo");
        int añoFabricacion = resultSet.getInt("añoFabricacion");
        int capacidadPasajeros = resultSet.getInt("capacidadPasajeros");
        int kilometrajeActual = resultSet.getInt("kilometrajeActual");
        String estadoActividad = resultSet.getString("estadoActividad");
        String estado = resultSet.getString("estado");
        int id_sucursal = resultSet.getInt("id_sucursal");
        int id_sucursalUbicacionActual = resultSet.getInt("id_sucursalUbicacionActual");
        return new Bus(id_bus,imagen,numeroPlaca,marca,modelo,añoFabricacion,capacidadPasajeros,kilometrajeActual,estadoActividad,estado,id_sucursal,id_sucursalUbicacionActual);
    }

    public void cambiarEstadoBus(int id_bus, String estadoActual) throws SQLException{
        crearConexion();
        String nuevoEstado = null;
        if(estadoActual.equalsIgnoreCase(Estado.HABILITADO.name())){
            nuevoEstado = Estado.DESHABILITADO.name();
        } else if (estadoActual.equalsIgnoreCase(Estado.DESHABILITADO.name())){
            nuevoEstado = Estado.HABILITADO.name();
        }

        if (nuevoEstado != null){
            String sqlRealizarCambio = "UPDATE Bus SET estado = ? WHERE id_bus = ?";
            PreparedStatement psHacerCambio = connection.prepareStatement(sqlRealizarCambio);
            psHacerCambio.setString(1,nuevoEstado);
            psHacerCambio.setInt(2,id_bus);
            psHacerCambio.execute();
        } else {
            throw new SQLException();
        }
        cerrarConexion();
    }

    public void actualizarDatosBus(int id_bus,int capacidad,int kilometraje) throws SQLException{
        crearConexion();
        try {
            connection.setAutoCommit(false);

            String sqlEliminarAsientos = "DELETE FROM Asiento WHERE id_bus = ?";
            PreparedStatement psEliminarAsientos = connection.prepareStatement(sqlEliminarAsientos);
            psEliminarAsientos.setInt(1,id_bus);
            psEliminarAsientos.execute();

            String sqlIngresarAsientos = "INSERT INTO Asiento(numero_asiento, id_bus) VALUES(?,?)";
            PreparedStatement psNuevosAsientos = connection.prepareStatement(sqlIngresarAsientos);
            psNuevosAsientos.setInt(2,id_bus);
            for (int i = 1; i <= capacidad; i++) {
                psNuevosAsientos.setInt(1,i);
                psNuevosAsientos.execute();
            }

            String sqlActualizarCapacidadPasajeros = "UPDATE Bus SET capacidadPasajeros = ? WHERE id_bus = ?";
            PreparedStatement psActualizarCapacidad = connection.prepareStatement(sqlActualizarCapacidadPasajeros);
            psActualizarCapacidad.setInt(1,capacidad);
            psActualizarCapacidad.setInt(2,id_bus);
            psActualizarCapacidad.execute();

            String sqlCambiarKilometraje = "UPDATE Bus SET kilometrajeActual = ? WHERE id_bus = ?";
            PreparedStatement psCambiarKilometraje = connection.prepareStatement(sqlCambiarKilometraje);
            psCambiarKilometraje.setInt(1,kilometraje);
            psCambiarKilometraje.setInt(2,id_bus);
            psCambiarKilometraje.execute();

            connection.commit();
            connection.setAutoCommit(true);
            cerrarConexion();


        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
                connection.setAutoCommit(true);
                cerrarConexion();
            }
            throw e;
        }
    }

    public void ingresarNuevoBus(int idSucursal,String imagen,String numeroPlaca, String marca, String modelo, int año, int capacidad, int kilometraje) throws SQLException{
        crearConexion();
        String sqlIngresarBus = "INSERT INTO Bus(id_sucursal, id_sucursalUbicacionActual, imagen, numeroPlaca, marca, modelo, añoFabricacion, capacidadPasajeros, kilometrajeActual) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlIngresarBus);
        preparedStatement.setInt(1,idSucursal);
        preparedStatement.setInt(2,idSucursal);
        preparedStatement.setString(3,imagen);
        preparedStatement.setString(4,numeroPlaca);
        preparedStatement.setString(5,marca);
        preparedStatement.setString(6,modelo);
        preparedStatement.setInt(7,año);
        preparedStatement.setInt(8,capacidad);
        preparedStatement.setInt(9,kilometraje);
        preparedStatement.execute();
        cerrarConexion();
    }

    public void eliminarBus(int idBus) throws SQLException{
        crearConexion();
        String sqlEliminar = "DELETE FROM Bus WHERE id_bus = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEliminar);
        preparedStatement.setInt(1,idBus);
        preparedStatement.execute();
        cerrarConexion();
    }
}
