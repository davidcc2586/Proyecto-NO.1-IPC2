package Objetos;

import java.time.LocalDate;
import java.time.LocalTime;

public class SolicitudViajePrivado {

    private int idSolicitudViajePrivado;
    private int cantidadPasajeros;
    private int distancia;
    private int idUsuario;
    private int idSucursal;
    private String direccionDestino;
    private double latitud;
    private double longitud;
    private LocalDate fechaSalida;
    private LocalTime horaSalida;
    private LocalDate fechaEstimadaRegreso;
    private LocalTime horaEstimadaRegreso;
    private double costo;
    private String estadoSolicitud;
    private String estadoPago;

    public SolicitudViajePrivado(int idSolicitudViajePrivado, int cantidadPasajeros, int distancia, int idUsuario, int idSucursal, String direccionDestino, double latitud, double longitud, LocalDate fechaSalida, LocalTime horaSalida, LocalDate fechaEstimadaRegreso, LocalTime horaEstimadaRegreso, double costo, String estadoSolicitud, String estadoPago) {
        this.idSolicitudViajePrivado = idSolicitudViajePrivado;
        this.cantidadPasajeros = cantidadPasajeros;
        this.distancia = distancia;
        this.idUsuario = idUsuario;
        this.idSucursal = idSucursal;
        this.direccionDestino = direccionDestino;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.fechaEstimadaRegreso = fechaEstimadaRegreso;
        this.horaEstimadaRegreso = horaEstimadaRegreso;
        this.costo = costo;
        this.estadoSolicitud = estadoSolicitud;
        this.estadoPago = estadoPago;
    }

    public int getIdSolicitudViajePrivado() {
        return idSolicitudViajePrivado;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public int getDistancia() {
        return distancia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
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
        return horaEstimadaRegreso;
    }

    public double getCosto() {
        return costo;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setIdSolicitudViajePrivado(int idSolicitudViajePrivado) {
        this.idSolicitudViajePrivado = idSolicitudViajePrivado;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
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
        this.horaEstimadaRegreso = horaEstimadaRegreso;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}