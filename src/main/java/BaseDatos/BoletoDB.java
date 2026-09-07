package BaseDatos;

import Logica.Exception.SaldoInsuficienteException;
import Objetos.Boleto;
import Objetos.Enums.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoletoDB {
    private Connection connection;

    public BoletoDB () {}

    public void registrarNuevoBoleto(int id_usuario, int id_viajeRegular, int numeroAsiento, double precio, LocalDate fechaCompra, int id_bus) throws SQLException, SaldoInsuficienteException {
        crearConexion();
        try {
            connection.setAutoCommit(false);

            String sqlSaldoUsuario = "SELECT saldoCartera FROM Usuario WHERE id_usuario = ?";
            double saldoUsuario = 0;
            PreparedStatement psSaldo = connection.prepareStatement(sqlSaldoUsuario);
            psSaldo.setInt(1, id_usuario);
            ResultSet rs = psSaldo.executeQuery();
            if (rs.next()) {
                saldoUsuario = rs.getDouble("saldoCartera");
            }



            if (saldoUsuario < precio) {
                throw new SaldoInsuficienteException();
            }

            String sqlInsertar = "INSERT INTO Boleto(id_usuario, id_viajeRegular, numeroAsiento, precio, fechaCompra) VALUES (?,?,?,?,?)";
            PreparedStatement psInsertar = connection.prepareStatement(sqlInsertar);
            psInsertar.setInt(1, id_usuario);
            psInsertar.setInt(2, id_viajeRegular);
            psInsertar.setInt(3, numeroAsiento);
            psInsertar.setDouble(4, precio);
            psInsertar.setDate(5, java.sql.Date.valueOf(fechaCompra));
            psInsertar.execute();

            String sqlOcuparAsiento = "UPDATE Asiento SET estado = ? WHERE id_bus = ? AND numero_asiento = ?";
            String ocupado = Estado.DESHABILITADO.name();
            PreparedStatement psAsiento = connection.prepareStatement(sqlOcuparAsiento);
            psAsiento.setString(1, ocupado);
            psAsiento.setInt(2, id_bus);
            psAsiento.setInt(3, numeroAsiento);
            psAsiento.execute();


            String sqlAumentarCantidadPasajeros = "UPDATE ViajeRegular SET pasajeros = pasajeros +1 WHERE id_viajeRegular = ?";
            PreparedStatement psAumentar = connection.prepareStatement(sqlAumentarCantidadPasajeros);
            psAumentar.setInt(1, id_viajeRegular);
            psAumentar.execute();


            String sqlRestarSaldoUsuario = "UPDATE Usuario SET saldoCartera = saldoCartera - ? WHERE id_usuario = ?";
            PreparedStatement psRestar = connection.prepareStatement(sqlRestarSaldoUsuario);
            psRestar.setDouble(1, precio);
            psRestar.setInt(2, id_usuario);
            psRestar.execute();


            connection.commit();
        } catch (SQLException | SaldoInsuficienteException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                cerrarConexion();
            }
        }
    }

    public List<Boleto> boletosUsuario(int id_usuario) throws SQLException{
        crearConexion();
        List<Boleto> boletos = new ArrayList<>();
        String sqlSolicitarBoletos = "SELECT * FROM Boleto WHERE id_usuario = ?";
        PreparedStatement psSolicitarBoletos = connection.prepareStatement(sqlSolicitarBoletos);
        psSolicitarBoletos.setInt(1, id_usuario);
        ResultSet resultSet = psSolicitarBoletos.executeQuery();
        while(resultSet.next()){
            Boleto boleto = crearBoleto(resultSet);
            boletos.add(boleto);
        }
        cerrarConexion();
        return boletos;
    }

    private Boleto crearBoleto(ResultSet resultSet) throws SQLException{
        int id_boleto = resultSet.getInt("id_boleto");
        int id_usuario = resultSet.getInt("id_usuario");
        int id_viajeRegular = resultSet.getInt("id_viajeRegular");
        int numeroAsiento = resultSet.getInt("numeroAsiento");
        double precio = resultSet.getDouble("precio");
        LocalDate fechaCompra = resultSet.getDate("fechaCompra").toLocalDate();
        return new Boleto(id_boleto,id_usuario,id_viajeRegular,numeroAsiento,precio,fechaCompra);
    }
    private void crearConexion(){
        ConexionDB conexionDB = new ConexionDB();
        connection = conexionDB.getConnection();
    }

    private void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}