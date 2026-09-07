package BaseDatos;

import Objetos.Enums.Estado;
import Objetos.Sucursal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucursalDB {
    private Connection connection ;

    public SucursalDB(){

    }

    public List<Sucursal> sucursalesActiva() throws SQLException {
        crearConexion();
        List<Sucursal> sucursales = new ArrayList<>();
        String sqlSolicitSucursales = "SELECT * FROM Sucursal where estado = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitSucursales);
        preparedStatement.setString(1,Estado.HABILITADO.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Sucursal sucursal = crearSucursal(resultSet);
            sucursales.add(sucursal);
        }
        cerrarConexion();
        return sucursales;
    }

    public Sucursal obtenerSucursal(int id_sucursal) throws SQLException{
        crearConexion();
        String sqlSolicitarSucursal = "SELECT * FROM Sucursal WHERE id_sucursal = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitarSucursal);
        preparedStatement.setInt(1,id_sucursal);
        ResultSet resultSet = preparedStatement.executeQuery();
        Sucursal sucursal = null;
        if(resultSet.next()){
            sucursal = crearSucursal(resultSet);
        }
        cerrarConexion();
        return sucursal;
    }

    public Sucursal crearSucursal(ResultSet resultSet) throws SQLException{
        int id_sucursal = resultSet.getInt("id_sucursal");
        String departamento = resultSet.getString("departamento");
        String municipio = resultSet.getString("municipio");
        double latitud = resultSet.getDouble("latitud");
        double longitud = resultSet.getDouble("longitud");
        String direccion = resultSet.getString("direccion");
        String telefono = resultSet.getString("telefono");
        String correo = resultSet.getString("correo");
        String estado = resultSet.getString("estado");
        return new Sucursal(id_sucursal, departamento,municipio,latitud,longitud,direccion,telefono,correo,estado);
    }

    private void crearConexion(){
        ConexionDB conexionDB = new ConexionDB();
        connection = conexionDB.getConnection();
    }

    private void cerrarConexion() throws SQLException {
        connection.close();
    }
}
