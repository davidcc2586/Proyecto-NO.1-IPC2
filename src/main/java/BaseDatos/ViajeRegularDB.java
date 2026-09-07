package BaseDatos;

import Objetos.Enums.EstadoViaje;
import Objetos.ViajeRegular;
import com.mysql.cj.PreparedQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViajeRegularDB {
    private Connection connection;

    public ViajeRegularDB() {

    }

    public List<ViajeRegular> viajesRegularesDisponibles(int id_Sucursal) throws SQLException {
        crearConexion();
        List<ViajeRegular> viajes = new ArrayList<>();
        String sqlSolicitarViajes = "SELECT * FROM ViajeRegular WHERE id_sucursal = ? AND estadoViaje = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitarViajes);
        preparedStatement.setInt(1, id_Sucursal);
        preparedStatement.setString(2, EstadoViaje.PROGRAMADO.name());
        ResultSet resultado = preparedStatement.executeQuery();
        while (resultado.next()) {
            ViajeRegular viajeRegular = crearViajeRegular(resultado);
            viajes.add(viajeRegular);
        }
        cerrarConexion();
        return viajes;
    }

    public ViajeRegular solicitarViajeRegular(int id_viaje) throws SQLException {
        crearConexion();
        String sqlSolicitarViaje = "SELECT  * FROM ViajeRegular where id_viajeRegular = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitarViaje);
        preparedStatement.setInt(1, id_viaje);
        ResultSet resultSet = preparedStatement.executeQuery();
        ViajeRegular viajeRegular = null;
        if(resultSet.next()){
            viajeRegular = crearViajeRegular(resultSet);
        }
        cerrarConexion();
        return viajeRegular;
    }


    public ViajeRegular crearViajeRegular(ResultSet resultado) throws SQLException{
        int idViaje = resultado.getInt("id_viajeRegular");
        int idSucursal = resultado.getInt("id_sucursal");
        int idbus = resultado.getInt("id_bus");
        int idChofer = resultado.getInt("id_chofer");
        int idRuta = resultado.getInt("id_rutaRegular");
        int pasajeros = resultado.getInt("pasajeros");
        LocalDate fechaSalida = resultado.getDate("fechaSalida").toLocalDate();
        LocalTime horaSalida = resultado.getTime("horaSalida").toLocalTime();
        LocalDate fechaEstimadaLlegada= resultado.getDate("fechaEstimadaLlegada").toLocalDate();
        LocalTime horaEstimadaLlegada = resultado.getTime("HoraEstimadaLlegada").toLocalTime();
        String estadoViaje = resultado.getString("estadoViaje");
        int idDetallesViaje = resultado.getInt("id_detallesViaje");

        return new ViajeRegular(idSucursal,idbus,idChofer,pasajeros,fechaSalida,horaSalida,fechaEstimadaLlegada,horaEstimadaLlegada,estadoViaje,idDetallesViaje,idViaje,idRuta);
    }

    private void crearConexion(){
        ConexionDB conexionDB = new ConexionDB();
        connection = conexionDB.getConnection();
    }

    private void cerrarConexion() throws SQLException {
        connection.close();
    }
}
