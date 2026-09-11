package BaseDatos;

import Objetos.RutaRegular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RutaRegularDB extends DB{
    public RutaRegularDB() {

    }

    public RutaRegular obtenerRutaRegular(int id_rutaRegular) throws SQLException{
        crearConexion();
        String sqlSolicitarRuta = "SELECT * FROM RutaRegular WHERE id_rutaRegular = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitarRuta);
        preparedStatement.setInt(1, id_rutaRegular);
        ResultSet resultSet = preparedStatement.executeQuery();
        RutaRegular rutaRegular = null;
        if (resultSet.next()){
            rutaRegular = crearRutaRegular(resultSet);
        }
        cerrarConexion();
        return rutaRegular;
    }


    public RutaRegular crearRutaRegular(ResultSet resultSet) throws SQLException{
        int id_rutaRegular = resultSet.getInt("id_rutaRegular");
        int id_sucursalInicio = resultSet.getInt("id_sucursalInicio");
        int id_sucursalDestino = resultSet.getInt("id_sucursalDestino");
        double distancia = resultSet.getDouble("distancia");
        double precio = resultSet.getDouble("precio");
        return new RutaRegular(id_rutaRegular,id_sucursalInicio,id_sucursalDestino,distancia,precio);
    }
}
