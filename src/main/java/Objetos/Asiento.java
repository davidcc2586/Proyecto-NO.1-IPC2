package Objetos;

public class Asiento {
    private int numero_asiento;
    private int id_bus;
    private String estado;

    public Asiento(int numero_asiento, int id_bus, String estado) {
        this.numero_asiento = numero_asiento;
        this.id_bus = id_bus;
        this.estado = estado;
    }

    public int getNumero_asiento() {
        return numero_asiento;
    }

    public int getId_bus() {
        return id_bus;
    }

    public String getEstado() {
        return estado;
    }

    public void setNumero_asiento(int numero_asiento) {
        this.numero_asiento = numero_asiento;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
