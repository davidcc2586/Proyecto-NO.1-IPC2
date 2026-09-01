package Objetos;

public class Transaccion {
    private int id_transaccion;
    private String tipo;
    private int id_detallesTransaccion;//ya se que se algun gasto de la empresa con el id o el id de la compra de un boleto o viaje de alquiler
    private double total;

    public Transaccion(int id_transaccion, String tipo, int id_detallesTransaccion, double total) {
        this.id_transaccion = id_transaccion;
        this.tipo = tipo;
        this.id_detallesTransaccion = id_detallesTransaccion;
        this.total = total;
    }

    public int getId_transaccion() {
        return id_transaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId_detallesTransaccion() {
        return id_detallesTransaccion;
    }

    public double getTotal() {
        return total;
    }
}
