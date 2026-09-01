package BaseDatos;

import Objetos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public String verificarDatosRepetidos(String dpi, String usuario, String correo, String nit) throws SQLException{
        crearConexion();
        String sqlSolicitarUsuarioMismoDpi = "SELECT * FROM Usuario WHERE dpi = ?";
        String sqlSolicitarUsuarioMismoUsuario = "SELECT * FROM Usuario WHERE usuario = ?";
        String sqlSolicitarUsuarioMismoCorreo = "SELECT * FROM Usuario WHERE correo = ?";
        String sqlSolicitarUsuarioMismoNit = "SELECT * FROM Usuario WHERE nit = ?";

        PreparedStatement preparedStatementPdi = connection.prepareStatement(sqlSolicitarUsuarioMismoDpi);
        preparedStatementPdi.setString(1, dpi);
        ResultSet resultadoDpi = preparedStatementPdi.executeQuery();
        if(resultadoDpi.next()){
            return "dpi";
        }

        PreparedStatement preparedStatementUsuario = connection.prepareStatement(sqlSolicitarUsuarioMismoUsuario);
        preparedStatementUsuario.setString(1, usuario);
        ResultSet resultadUsuario = preparedStatementUsuario.executeQuery();
        if(resultadUsuario.next()){
            return "usuario";
        }

        PreparedStatement preparedStatementCorreo = connection.prepareStatement(sqlSolicitarUsuarioMismoCorreo);
        preparedStatementCorreo.setString(1, correo);
        ResultSet resultadCorreo = preparedStatementCorreo.executeQuery();
        if(resultadCorreo.next()){
            return "correo";
        }

        PreparedStatement preparedStatementNit = connection.prepareStatement(sqlSolicitarUsuarioMismoNit);
        preparedStatementNit.setString(1, nit);
        ResultSet resultadoNit = preparedStatementNit.executeQuery();
        if(resultadoNit.next()){
            return "nit";
        }
        cerrarConexion();
        return "ninguno";
    }

    public List<Usuario> ObtenerElementosDB() throws SQLException {
        crearConexion();
        List<Usuario> usuarios = new ArrayList<>();
        String consultarUsuariosExistente = "SELECT * FROM Usuario";

        PreparedStatement preparedStatement = connection.prepareStatement(consultarUsuariosExistente);
        ResultSet resultado = preparedStatement.executeQuery();
        while (resultado.next()){

        }
        cerrarConexion();
        return usuarios;
    }

    private void crearConexion(){
        ConexionDB conexionDB = new ConexionDB();
        connection = conexionDB.getConnection();
    }

    private void cerrarConexion() throws SQLException {
        connection.close();
    }
}
