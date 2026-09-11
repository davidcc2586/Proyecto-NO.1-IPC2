package Objetos;

import java.time.LocalDate;
import java.time.LocalTime;

public class ViajeRegular extends Viaje{
    private int id_viajeRegular;
    private int id_rutaRegular;

    public ViajeRegular(int id_sucursal, int id_bus, int id_chofer, int cantidadPasajeros, LocalDate fechaSalida, LocalTime horaSalida, LocalDate fechaEstimadaLlegada, LocalTime horaEstimadaLlegada, String estadoViaje, int id_viajeRegular, int id_rutaRegular) {
        super(id_sucursal, id_bus, id_chofer, cantidadPasajeros, fechaSalida, horaSalida, fechaEstimadaLlegada, horaEstimadaLlegada, estadoViaje);
        this.id_viajeRegular = id_viajeRegular;
        this.id_rutaRegular = id_rutaRegular;
    }

    public int getId_viajeRegular() {
        return id_viajeRegular;
    }

    public int getId_rutaRegular() {
        return id_rutaRegular;
    }

    public void setId_rutaRegular(int id_rutaRegular) {
        this.id_rutaRegular = id_rutaRegular;
    }

    public void setId_viajeRegular(int id_viajeRegular) {
        this.id_viajeRegular = id_viajeRegular;
    }
}
