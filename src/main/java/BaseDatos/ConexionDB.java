package BaseDatos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    private final String HOST = "localhost";
    private final String PUERTO = "3306";
    private final String URL = "jdbc:mysql://" + HOST + ":" + PUERTO ; //   jdbc:mysql://localhost:3306
    private final String URLBASEDATOS = URL + "/BusesExtraurbanosXela";
    private String USUARIO = "josue";
    private String CONTRASEÑA = "josue100000";
    private Connection connection;

    public ConexionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URLBASEDATOS, USUARIO, CONTRASEÑA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
