package BaseDatos;

import Objetos.Chofer;
import Objetos.Enums.Estado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChoferDB extends DB{

    public ChoferDB (){

    }

    public List<Chofer> choferesSucursal(int idSucursal) throws SQLException {
        List<Chofer> choferes = new ArrayList<>();
        crearConexion();
        String sqlSolicitarChofer = "SELECT * FROM Chofer WHERE id_sucursal = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitarChofer);
        preparedStatement.setInt(1,idSucursal);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Chofer chofer = crearChofer(resultSet);
            choferes.add(chofer);
        }
        cerrarConexion();
        return choferes;
    }

    public Optional<Chofer> choferSucursal(int idChofer) throws SQLException {
        crearConexion();
        Optional<Chofer> chofer = Optional.empty();
        String sqlSolicitarChofer = "SELECT * FROM Chofer WHERE id_chofer = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitarChofer);
        preparedStatement.setInt(1,idChofer);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            chofer = Optional.of(crearChofer(resultSet));
        }
        cerrarConexion();
        return chofer;
    }

    public void cambiarEstadoChofer(int id_chofer, String estadoActual) throws SQLException {
        crearConexion();
        String nuevoEstado = null;
        if(estadoActual.equalsIgnoreCase(Estado.HABILITADO.name())){
            nuevoEstado = Estado.DESHABILITADO.name();
        } else if (estadoActual.equalsIgnoreCase(Estado.DESHABILITADO.name())){
            nuevoEstado = Estado.HABILITADO.name();
        }

        if (nuevoEstado != null){
            String sqlRealizarCambio = "UPDATE Chofer SET estado = ? WHERE id_chofer = ?";
            PreparedStatement psHacerCambio = connection.prepareStatement(sqlRealizarCambio);
            psHacerCambio.setString(1,nuevoEstado);
            psHacerCambio.setInt(2,id_chofer);
            psHacerCambio.execute();
        } else {
            throw new SQLException();
        }
        cerrarConexion();
    }

    public void eliminarChofer(int idChofer) throws SQLException {
        crearConexion();
        String sqlEliminar = "DELETE FROM Chofer WHERE id_chofer = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlEliminar);
        preparedStatement.setInt(1,idChofer);
        preparedStatement.execute();
        cerrarConexion();
    }

    public void actualizarChofer(int idChofer, LocalDate vencimientoLicencia, String telefono, double salarioViaje) throws  SQLException{
        crearConexion();
        String sqlActualizar = "UPDATE Chofer SET vencimientoLicencia = ?, telefono = ?,salarioBaseViaje = ? WHERE  id_chofer = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlActualizar);
        preparedStatement.setDate(1, java.sql.Date.valueOf(vencimientoLicencia));
        preparedStatement.setString(2, telefono);
        preparedStatement.setDouble(3, salarioViaje);
        preparedStatement.setInt(4, idChofer);
        preparedStatement.execute();
        cerrarConexion();
    }

    public void ingresarNuevoChofer(int id_sucursal, String imagen, String nombre, String apellido, String licencia, String tipoLicencia, LocalDate vencimientoLicencia,String telefono, double salarioBaseViaje) throws SQLException {
        crearConexion();
        String sqlIngresarNuevoChofer = "INSERT INTO Chofer(id_sucursal, imagen, nombre, apellido, licencia, tipoLicencia, vencimientoLicencia, telefono, salarioBaseViaje) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlIngresarNuevoChofer);
        preparedStatement.setInt(1, id_sucursal);
        preparedStatement.setString(2, imagen);
        preparedStatement.setString(3, nombre);
        preparedStatement.setString(4, apellido);
        preparedStatement.setString(5, licencia);
        preparedStatement.setString(6, tipoLicencia);
        preparedStatement.setDate(7, java.sql.Date.valueOf(vencimientoLicencia));
        preparedStatement.setString(8,telefono);
        preparedStatement.setDouble(9,salarioBaseViaje);
        preparedStatement.execute();
        cerrarConexion();
    }


    public Chofer crearChofer(ResultSet resultSet) throws SQLException{
        int id_chofer = resultSet.getInt("id_chofer");
        int id_sucursal = resultSet.getInt("id_sucursal");
        String imagen = resultSet.getString("imagen");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        String licencia = resultSet.getString("licencia");
        String tipoLicencia = resultSet.getString("tipoLicencia");
        LocalDate vencimientoLicencia = resultSet.getDate("vencimientoLicencia").toLocalDate();
        String telefono = resultSet.getString("telefono");
        Double salarioBaseViaje = resultSet.getDouble("salarioBaseViaje");
        String estadoActividad = resultSet.getString("estadoActividad");
        String estado = resultSet.getString("estado");
        return new Chofer(id_chofer,imagen,nombre,apellido,licencia,tipoLicencia,vencimientoLicencia,telefono,salarioBaseViaje,estadoActividad,estado,id_sucursal);
    }
}
