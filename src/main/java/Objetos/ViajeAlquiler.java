package Objetos;

import java.time.LocalDate;
import java.time.LocalTime;

public class ViajeAlquiler extends Viaje {
    private int id_viajeAlquiler;
    private int id_usuarioContratista;
    private int id_rutaPrivada;

    public ViajeAlquiler(int id_bus, int id_chofer, int cantidadPasajeros, LocalDate fechaSalida, LocalTime horaSalida, LocalDate fechaEstimadaRegreso, LocalTime horaEstimadaRegreso, String estadoViaje, int id_detallesViaje, int id_viajeAlquiler, int id_usuarioContratista, int id_rutaPrivada) {
        super(id_bus, id_chofer, cantidadPasajeros, fechaSalida, horaSalida, fechaEstimadaRegreso, horaEstimadaRegreso, estadoViaje, id_detallesViaje);
        this.id_viajeAlquiler = id_viajeAlquiler;
        this.id_usuarioContratista = id_usuarioContratista;
        this.id_rutaPrivada = id_rutaPrivada;
    }

    public int getId_viajeAlquiler() {
        return id_viajeAlquiler;
    }

    public int getId_usuarioContratista() {
        return id_usuarioContratista;
    }

    public int getId_rutaPrivada() {
        return id_rutaPrivada;
    }

    public void setId_rutaPrivada(int id_rutaPrivada) {
        this.id_rutaPrivada = id_rutaPrivada;
    }

    public void setId_viajeAlquiler(int id_viajeAlquiler) {
        this.id_viajeAlquiler = id_viajeAlquiler;
    }

    public void setId_usuarioContratista(int id_usuarioContratista) {
        this.id_usuarioContratista = id_usuarioContratista;
    }
}
