package Objetos;

import java.time.LocalDate;

public class GastoTaller {
    private int id_gastoTaller;
    private int id_bus;
    private double montoManoObra;
    private double montoRepuesto;
    private LocalDate fechaMantenimiento;

    public GastoTaller(int id_gastoTaller, int id_bus, double montoManoObra, double montoRepuesto, LocalDate fechaMantenimiento) {
        this.id_gastoTaller = id_gastoTaller;
        this.id_bus = id_bus;
        this.montoManoObra = montoManoObra;
        this.montoRepuesto = montoRepuesto;
        this.fechaMantenimiento = fechaMantenimiento;
    }

    public int getId_gastoTaller() {
        return id_gastoTaller;
    }

    public int getId_bus() {
        return id_bus;
    }

    public double getMontoManoObra() {
        return montoManoObra;
    }

    public double getMontoRepuesto() {
        return montoRepuesto;
    }

    public LocalDate getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setId_gastoTaller(int id_gastoTaller) {
        this.id_gastoTaller = id_gastoTaller;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public void setMontoManoObra(double montoManoObra) {
        this.montoManoObra = montoManoObra;
    }

    public void setMontoRepuesto(double montoRepuesto) {
        this.montoRepuesto = montoRepuesto;
    }

    public void setFechaMantenimiento(LocalDate fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }
}
