package BaseDatos;

import Objetos.Asiento;
import Objetos.Enums.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsientoDB {
    private Connection connection;

    public List<Asiento> obtenerAsientosDisponibles(int id_bus) throws SQLException{
        crearConexion();
        List<Asiento> asientos = new ArrayList<>();
        String estadoLibre = Estado.HABILITADO.name();
        String sqlSolicite = "SELECT * FROM Asiento WHERE id_bus = ? AND estado = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicite);
        preparedStatement.setInt(1, id_bus);
        preparedStatement.setString(2,estadoLibre);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Asiento asiento = crearAsiento(resultSet);
            asientos.add(asiento);
        }
        cerrarConexion();
        return asientos;
    }

    public Asiento crearAsiento(ResultSet resultSet) throws SQLException{
        int numero_asiento = resultSet.getInt("numero_asiento");
        int id_bus = resultSet.getInt("id_bus");
        String estado = resultSet.getString("estado");
        return new Asiento(numero_asiento,id_bus,estado);
    }

    private void crearConexion(){
        ConexionDB conexionDB = new ConexionDB();
        connection = conexionDB.getConnection();
    }

    private void cerrarConexion() throws SQLException {
        connection.close();
    }
}
