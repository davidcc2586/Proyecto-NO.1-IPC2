package Objetos;

import java.time.LocalTime;

public class DetallesViaje {

    private int id_detalleViaje;
    private int id_viaje;
    private LocalTime horaSalida;
    private int KilometrajeInicio;
    private LocalTime horaLlegada;
    private int kilometrajeLlegada;
    private double combustibleUtilizado;
    private double salarioChofer;
    private double aproximadoDepreciacion;

    public DetallesViaje(){

    }

    public DetallesViaje(int id_detalleViaje, int id_viaje, LocalTime horaSalida, int kilometrajeInicio, LocalTime horaLlegada, int kilometrajeLlegada, double combustibleUtilizado, double salarioChofer, double aproximadoDepreciacion) {
        this.id_detalleViaje = id_detalleViaje;
        this.id_viaje = id_viaje;
        this.horaSalida = horaSalida;
        KilometrajeInicio = kilometrajeInicio;
        this.horaLlegada = horaLlegada;
        this.kilometrajeLlegada = kilometrajeLlegada;
        this.combustibleUtilizado = combustibleUtilizado;
        this.salarioChofer = salarioChofer;
        this.aproximadoDepreciacion = aproximadoDepreciacion;
    }

    public int getId_detalleViaje() {
        return id_detalleViaje;
    }

    public int getId_viaje() {
        return id_viaje;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public int getKilometrajeInicio() {
        return KilometrajeInicio;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public int getKilometrajeLlegada() {
        return kilometrajeLlegada;
    }

    public double getCombustibleUtilizado() {
        return combustibleUtilizado;
    }

    public double getSalarioChofer() {
        return salarioChofer;
    }

    public double getAproximadoDepreciacion() {
        return aproximadoDepreciacion;
    }

    public void setId_detalleViaje(int id_detalleViaje) {
        this.id_detalleViaje = id_detalleViaje;
    }

    public void setId_viaje(int id_viaje) {
        this.id_viaje = id_viaje;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setKilometrajeInicio(int kilometrajeInicio) {
        KilometrajeInicio = kilometrajeInicio;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void setKilometrajeLlegada(int kilometrajeLlegada) {
        this.kilometrajeLlegada = kilometrajeLlegada;
    }

    public void setCombustibleUtilizado(double combustibleUtilizado) {
        this.combustibleUtilizado = combustibleUtilizado;
    }

    public void setSalarioChofer(double salarioChofer) {
        this.salarioChofer = salarioChofer;
    }

    public void setAproximadoDepreciacion(double aproximadoDepreciacion) {
        this.aproximadoDepreciacion = aproximadoDepreciacion;
    }
}
