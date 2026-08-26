package Logica.Objetos;

import java.time.LocalDate;

public class GastoCombustible {
    private int id_gastoCombustible;
    private double cantidadCombustible;
    private LocalDate fechaGasto;

    public GastoCombustible(int id_gastoCombustible, double cantidadCombustible, LocalDate fechaGasto) {
        this.id_gastoCombustible = id_gastoCombustible;
        this.cantidadCombustible = cantidadCombustible;
        this.fechaGasto = fechaGasto;
    }

    public int getId_gastoCombustible() {
        return id_gastoCombustible;
    }

    public double getCantidadCombustible() {
        return cantidadCombustible;
    }

    public LocalDate getFechaGasto() {
        return fechaGasto;
    }

    public void setId_gastoCombustible(int id_gastoCombustible) {
        this.id_gastoCombustible = id_gastoCombustible;
    }

    public void setCantidadCombustible(double cantidadCombustible) {
        this.cantidadCombustible = cantidadCombustible;
    }

    public void setFechaGasto(LocalDate fechaGasto) {
        this.fechaGasto = fechaGasto;
    }
}
