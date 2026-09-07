package BaseDatos;

import Objetos.Enums.EstadoSolicitudViajePrivado;
import Objetos.Usuario;

import javax.naming.PartialResultException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioDB {
    private Connection connection;

    public UsuarioDB(){

    }

    public Optional<Usuario> inicioSesionUsuario(String usuarioIngresado, String claveIngresado) throws SQLException{
        crearConexion();
        Optional<Usuario> usuario = Optional.empty();
        String verificarDatos = "SELECT * FROM Usuario WHERE usuario = ? AND clave = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(verificarDatos);
        preparedStatement.setString(1, usuarioIngresado);
        preparedStatement.setString(2, claveIngresado);
        ResultSet resultado = preparedStatement.executeQuery();
        if(resultado.next()){
            int id_usuario = resultado.getInt("id_usuario");
            String nombreUsuario = resultado.getString("usuario");
            String rol = resultado.getString("rol");
            String nombre = resultado.getString("nombre");
            String apellido = resultado.getString("apellido");
            String dpi = resultado.getNString("dpi");
            String telefono = resultado.getString("telefono");
            String direccion = resultado.getString("direccion");
            String correo = resultado.getNString("correo");
            String nit = resultado.getString("nit");
            double saldoCartera = resultado.getDouble("saldoCartera");
            usuario = Optional.of(new Usuario(id_usuario,nombreUsuario,rol,nombre,apellido,dpi,telefono,direccion,correo,nit,saldoCartera));
        }
        cerrarConexion();
        return usuario;
    }

    public String ingresarNuevoUsuario(String usuario, String clave, String rol, String nombre, String apellido, String dpi, String telefono, String direccion, String correo, String nit) throws SQLException{
        String elementoRepetido = verificarDatosRepetidos(dpi, usuario, correo, nit);

        if(elementoRepetido.equalsIgnoreCase("ninguno")){
        crearConexion();
        String ingresarUsuarioDB = "INSERT INTO Usuario(usuario,clave,rol,nombre,apellido,dpi,telefono,direccion,correo,nit) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(ingresarUsuarioDB);
        preparedStatement.setString(1, usuario);
        preparedStatement.setString(2, clave);
        preparedStatement.setString(3, rol);
        preparedStatement.setString(4, nombre);
        preparedStatement.setString(5, apellido);
        preparedStatement.setString(6, dpi);
        preparedStatement.setString(7,telefono);
        preparedStatement.setString(8, direccion);
        preparedStatement.setString(9, correo);
        preparedStatement.setString(10, nit);
        preparedStatement.execute();
        cerrarConexion();
        }

        return elementoRepetido;
    }

    public String verificarDatosRepetidos(String dpi, String usuario, String correo, String nit) throws SQLException {
        crearConexion();

        try {
            connection.setAutoCommit(false);

            String sqlSolicitarUsuarioMismoDpi = "SELECT * FROM Usuario WHERE dpi = ?";
            String sqlSolicitarUsuarioMismoUsuario = "SELECT * FROM Usuario WHERE usuario = ?";
            String sqlSolicitarUsuarioMismoCorreo = "SELECT * FROM Usuario WHERE correo = ?";
            String sqlSolicitarUsuarioMismoNit = "SELECT * FROM Usuario WHERE nit = ?";

            PreparedStatement preparedStatementPdi = connection.prepareStatement(sqlSolicitarUsuarioMismoDpi);
            preparedStatementPdi.setString(1, dpi);
            ResultSet resultadoDpi = preparedStatementPdi.executeQuery();
            if (resultadoDpi.next()) {
                connection.commit();
                connection.setAutoCommit(true);
                cerrarConexion();
                return "dpi";
            }

            PreparedStatement preparedStatementUsuario = connection.prepareStatement(sqlSolicitarUsuarioMismoUsuario);
            preparedStatementUsuario.setString(1, usuario);
            ResultSet resultadUsuario = preparedStatementUsuario.executeQuery();
            if (resultadUsuario.next()) {
                connection.commit();
                connection.setAutoCommit(true);
                cerrarConexion();
                return "usuario";
            }

            PreparedStatement preparedStatementCorreo = connection.prepareStatement(sqlSolicitarUsuarioMismoCorreo);
            preparedStatementCorreo.setString(1, correo);
            ResultSet resultadCorreo = preparedStatementCorreo.executeQuery();
            if (resultadCorreo.next()) {
                connection.commit();
                connection.setAutoCommit(true);
                cerrarConexion();
                return "correo";
            }

            PreparedStatement preparedStatementNit = connection.prepareStatement(sqlSolicitarUsuarioMismoNit);
            preparedStatementNit.setString(1, nit);
            ResultSet resultadoNit = preparedStatementNit.executeQuery();
            if (resultadoNit.next()) {
                connection.commit();
                connection.setAutoCommit(true);
                cerrarConexion();
                return "nit";
            }

            connection.commit();
            connection.setAutoCommit(true);
            cerrarConexion();
            return "ninguno";

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
                connection.setAutoCommit(true);
                cerrarConexion();
            }
            throw e;
        }
    }

    public void agregarSaldoCartera(int idUsuario, double cantidadAgregar) throws SQLException {
        crearConexion();
        String sqlAgregarSalfo = "UPDATE Usuario SET saldoCartera = saldoCartera + ? WHERE id_usuario = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlAgregarSalfo);
        preparedStatement.setDouble(1, cantidadAgregar);
        preparedStatement.setInt(2, idUsuario);
        preparedStatement.execute();
        cerrarConexion();
    }

    public void  actualizarDatos(int id, String telefono, String direccion, String correo, String usuario) throws SQLException{
        crearConexion();
        String ingresarUsuarioDB = "UPDATE Usuario SET telefono = ?, direccion = ?, correo = ?, usuario = ? WHERE id_usuario = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(ingresarUsuarioDB);
        preparedStatement.setString(1,telefono);
        preparedStatement.setString(2, direccion);
        preparedStatement.setString(3, correo);
        preparedStatement.setString(4, usuario);
        preparedStatement.setInt(5,id);
        preparedStatement.execute();
        cerrarConexion();
    }

    public void  actualizarDatos(int id, String telefono, String direccion, String correo, String usuario, String clave) throws SQLException{
        crearConexion();
        String ingresarUsuarioDB = "UPDATE Usuario SET telefono = ?, direccion = ?, correo = ?, usuario = ?, clave = ? WHERE id_usuario = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(ingresarUsuarioDB);
        preparedStatement.setString(1,telefono);
        preparedStatement.setString(2, direccion);
        preparedStatement.setString(3, correo);
        preparedStatement.setString(4, usuario);
        preparedStatement.setString(5, clave);
        preparedStatement.setInt(6,id);
        preparedStatement.execute();
        cerrarConexion();
    }

    public void pagarSolicitudViaje(int idUsuario, int idSolicitudViaje, double cantidad) throws SQLException {
        crearConexion();

        try {
            connection.setAutoCommit(false);

            String sqlRestarCantidad = "UPDATE Usuario SET saldoCartera = saldoCartera - ? WHERE id_usuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRestarCantidad);
            preparedStatement.setDouble(1,cantidad);
            preparedStatement.setInt(2,idUsuario);
            preparedStatement.execute();

            String sqlActualizarSolicitud = "UPDATE SolicitudViajePrivado SET estadoPago = ? WHERE id_solicitudViajePrivado = ?";
            String nuevoEstadoPago = EstadoSolicitudViajePrivado.CANCELADO.name();
            PreparedStatement psActualizarSolicitud = connection.prepareStatement(sqlActualizarSolicitud);
            psActualizarSolicitud.setString(1,nuevoEstadoPago);
            psActualizarSolicitud.setInt(2,idSolicitudViaje);
            psActualizarSolicitud.execute();


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


    private void crearConexion(){
        ConexionDB conexionDB = new ConexionDB();
        connection = conexionDB.getConnection();
    }

    private void cerrarConexion() throws SQLException {
        connection.close();
    }
}
