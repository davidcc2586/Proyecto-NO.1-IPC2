package Logica.Objetos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Viaje {
    protected int id_bus;
    protected int id_chofer;
    protected int cantidadPasajeros;
    protected LocalDate fechaSalida;
    protected LocalTime horaSalida;
    protected LocalDate fechaEstimadaRegreso;
    protected LocalTime HoraEstimadaRegreso;
    protected String estadoViaje;
    protected int id_detallesViaje;

    public Viaje(int id_bus, int id_chofer, int cantidadPasajeros, LocalDate fechaSalida, LocalTime horaSalida, LocalDate fechaEstimadaRegreso, LocalTime horaEstimadaRegreso, String estadoViaje, int id_detallesViaje) {
        this.id_bus = id_bus;
        this.id_chofer = id_chofer;
        this.cantidadPasajeros = cantidadPasajeros;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.fechaEstimadaRegreso = fechaEstimadaRegreso;
        HoraEstimadaRegreso = horaEstimadaRegreso;
        this.estadoViaje = estadoViaje;
        this.id_detallesViaje = id_detallesViaje;
    }

    public int getId_bus() {
        return id_bus;
    }

    public int getId_chofer() {
        return id_chofer;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public LocalDate getFechaEstimadaRegreso() {
        return fechaEstimadaRegreso;
    }

    public LocalTime getHoraEstimadaRegreso() {
        return HoraEstimadaRegreso;
    }

    public String getEstadoViaje() {
        return estadoViaje;
    }

    public int getId_detallesViaje() {
        return id_detallesViaje;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public void setId_chofer(int id_chofer) {
        this.id_chofer = id_chofer;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setFechaEstimadaRegreso(LocalDate fechaEstimadaRegreso) {
        this.fechaEstimadaRegreso = fechaEstimadaRegreso;
    }

    public void setHoraEstimadaRegreso(LocalTime horaEstimadaRegreso) {
        HoraEstimadaRegreso = horaEstimadaRegreso;
    }

    public void setEstadoViaje(String estadoViaje) {
        this.estadoViaje = estadoViaje;
    }

    public void setId_detallesViaje(int id_detallesViaje) {
        this.id_detallesViaje = id_detallesViaje;
    }
}
