package Objetos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Viaje {
    protected int id_sucursal;
    protected int id_bus;
    protected int id_chofer;
    protected int cantidadPasajeros;
    protected LocalDate fechaSalida;
    protected LocalTime horaSalida;
    protected LocalDate fechaEstimadaLlegada;
    protected LocalTime HoraEstimadaLlegada;
    protected String estadoViaje;
    protected int id_detallesViaje;

    public Viaje(int id_sucursal, int id_bus, int id_chofer, int cantidadPasajeros, LocalDate fechaSalida, LocalTime horaSalida, LocalDate fechaEstimadaLlegada, LocalTime horaEstimadaLlegada, String estadoViaje, int id_detallesViaje) {
        this.id_sucursal = id_sucursal;
        this.id_bus = id_bus;
        this.id_chofer = id_chofer;
        this.cantidadPasajeros = cantidadPasajeros;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.fechaEstimadaLlegada = fechaEstimadaLlegada;
        HoraEstimadaLlegada = horaEstimadaLlegada;
        this.estadoViaje = estadoViaje;
        this.id_detallesViaje = id_detallesViaje;
    }

    public int getId_sucursal() {
        return id_sucursal;
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

    public LocalDate getFechaEstimadaLlegada() {
        return fechaEstimadaLlegada;
    }

    public LocalTime getHoraEstimadaLlegada() {
        return HoraEstimadaLlegada;
    }

    public String getEstadoViaje() {
        return estadoViaje;
    }

    public int getId_detallesViaje() {
        return id_detallesViaje;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
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

    public void setFechaEstimadaLlegada(LocalDate fechaEstimadaLlegada) {
        this.fechaEstimadaLlegada = fechaEstimadaLlegada;
    }

    public void setHoraEstimadaLlegada(LocalTime horaEstimadaLlegada) {
        HoraEstimadaLlegada = horaEstimadaLlegada;
    }

    public void setEstadoViaje(String estadoViaje) {
        this.estadoViaje = estadoViaje;
    }

    public void setId_detallesViaje(int id_detallesViaje) {
        this.id_detallesViaje = id_detallesViaje;
    }
}
