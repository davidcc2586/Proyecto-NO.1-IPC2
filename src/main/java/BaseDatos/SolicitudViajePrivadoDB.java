package BaseDatos;

import Objetos.SolicitudViajePrivado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SolicitudViajePrivadoDB {

    private Connection connection ;

    public SolicitudViajePrivadoDB(){

    }

    public void ingresarNuevaSolicitud(int cantidadPasajeros, double distancia, int idUsuario, int idSucursal, String direccion, double latitud, double longitud, LocalDate fechaSalida, LocalTime horaSalida, LocalDate fechaRegreso, LocalTime horaRegreso, double costo) throws SQLException{
        crearConexion();
        String sqlIngresarSolicitud = "INSERT INTO SolicitudViajePrivado(cantidadPasajeros, distancia, id_usuario, id_sucursal, direccionDestino, latitud, longitud, fechaSalida, horaSalida, fechaEstimadaRegreso, HoraEstimadaRegreso, costo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement psIngresar = connection.prepareStatement(sqlIngresarSolicitud);
        psIngresar.setInt(1,cantidadPasajeros);
        psIngresar.setDouble(2,distancia);
        psIngresar.setInt(3,idUsuario);
        psIngresar.setInt(4,idSucursal);
        psIngresar.setString(5,direccion);
        psIngresar.setDouble(6,latitud);
        psIngresar.setDouble(7,longitud);
        psIngresar.setDate(8, java.sql.Date.valueOf(fechaSalida));
        psIngresar.setTime(9,java.sql.Time.valueOf(horaSalida));
        psIngresar.setDate(10, java.sql.Date.valueOf(fechaRegreso));
        psIngresar.setTime(11,java.sql.Time.valueOf(horaRegreso));
        psIngresar.setDouble(12,costo);
        psIngresar.execute();
        cerrarConexion();
    }

    public List<SolicitudViajePrivado> solicitudesUsuario(int id_usuario) throws SQLException {
        List<SolicitudViajePrivado> solicitudes = new ArrayList<>();
        crearConexion();
        String sqlSolicitar = "SELECT * FROM SolicitudViajePrivado WHERE id_usuario = ?";
        PreparedStatement psSolicitarSolicitudes = connection.prepareStatement(sqlSolicitar);
        psSolicitarSolicitudes.setInt(1,id_usuario);
        ResultSet resultSet = psSolicitarSolicitudes.executeQuery();
        while (resultSet.next()){
            SolicitudViajePrivado solicitudViajePrivado = crearSolicitud(resultSet);
            solicitudes.add(solicitudViajePrivado);
        }
        cerrarConexion();
        return solicitudes;
    }

    public SolicitudViajePrivado crearSolicitud(ResultSet resultSet) throws SQLException {
        int idSolicitudViajePrivado = resultSet.getInt("id_solicitudViajePrivado");
        int cantidadPasajeros = resultSet.getInt("cantidadPasajeros");
        int distancia = resultSet.getInt("distancia");
        int idUsuario = resultSet.getInt("id_usuario");
        int idSucursal = resultSet.getInt("id_sucursal");
        String direccionDestino = resultSet.getString("direccionDestino");
        double latitud  = resultSet.getDouble("latitud");
        double longitud  = resultSet.getDouble("longitud");
        LocalDate fechaSalida  = resultSet.getDate("fechaSalida").toLocalDate();
        LocalTime horaSalida = resultSet.getTime("horaSalida").toLocalTime();
        LocalDate fechaEstimadaRegreso = resultSet.getDate("fechaEstimadaRegreso").toLocalDate();
        LocalTime horaEstimadaRegreso= resultSet.getTime("HoraEstimadaRegreso").toLocalTime();
        double costo  = resultSet.getDouble("costo");
        String estadoSolicitud = resultSet.getString("estadoSolicitud");
        String estadoPago = resultSet.getString("estadoPago");
        return new SolicitudViajePrivado(idSolicitudViajePrivado,cantidadPasajeros,distancia,idUsuario,idSucursal,direccionDestino,latitud,longitud,fechaSalida,horaSalida,fechaEstimadaRegreso,horaEstimadaRegreso,costo,estadoSolicitud, estadoPago);
    }

    private void crearConexion(){
        ConexionDB conexionDB = new ConexionDB();
        connection = conexionDB.getConnection();
    }

    private void cerrarConexion() throws SQLException {
        connection.close();
    }
}
