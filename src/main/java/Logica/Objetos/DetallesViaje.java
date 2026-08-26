package Logica.Objetos;

import java.time.LocalTime;

public class DetallesViaje {

    private int id_detalleViaje;
    private int id_bus;
    private int id_chofer;
    private LocalTime horaSalida;
    private int KilometrajeInicio;
    private LocalTime horaLlegada;
    private int kilometrajeLlegada;
    private double combustibleUtilizado;
    private double salarioChofer;
    private double aproximadoDepreciacion;

    public DetallesViaje(){

    }

    public DetallesViaje(int id_detalleViaje, int id_bus, int id_chofer, LocalTime horaSalida, int kilometrajeInicio, LocalTime horaLlegada, int kilometrajeLlegada, double combustibleUtilizado, double salarioChofer, double aproximadoDepreciacion) {
        this.id_detalleViaje = id_detalleViaje;
        this.id_bus = id_bus;
        this.id_chofer = id_chofer;
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

    public int getId_bus() {
        return id_bus;
    }

    public int getId_chofer() {
        return id_chofer;
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

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public void setId_chofer(int id_chofer) {
        this.id_chofer = id_chofer;
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
