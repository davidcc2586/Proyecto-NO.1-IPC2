package Objetos;

import java.time.LocalDate;

public class GastoCombustible {
    private int id_gastoCombustible;
    private int id_bus;
    private double galones;
    private LocalDate fecha;

    public GastoCombustible(int id_gastoCombustible, int id_bus, double galones, LocalDate fecha) {
        this.id_gastoCombustible = id_gastoCombustible;
        this.id_bus = id_bus;
        this.galones = galones;
        this.fecha = fecha;
    }

    public int getId_gastoCombustible() {
        return id_gastoCombustible;
    }

    public int getId_bus() {
        return id_bus;
    }

    public double getGalones() {
        return galones;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setId_gastoCombustible(int id_gastoCombustible) {
        this.id_gastoCombustible = id_gastoCombustible;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public void setGalones(double galones) {
        this.galones = galones;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
