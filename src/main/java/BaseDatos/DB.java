package BaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class DB {
    protected Connection connection;

    protected void crearConexion(){
        ConexionDB conexionDB = new ConexionDB();
        connection = conexionDB.getConnection();
    }

    protected void cerrarConexion() throws SQLException {
        connection.close();
    }
}
