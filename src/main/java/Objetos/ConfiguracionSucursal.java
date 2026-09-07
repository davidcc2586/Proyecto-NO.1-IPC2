package Objetos;

public class ConfiguracionSucursal {
    private int id_configuracion;
    private int id_sucursal;
    private double depreciacionPorKm;
    private double costoCombustible;


    public ConfiguracionSucursal(int id_configuracion, int id_sucursal, double depreciacionPorKm, double costoCombustible) {
        this.id_configuracion = id_configuracion;
        this.id_sucursal = id_sucursal;
        this.depreciacionPorKm = depreciacionPorKm;
        this.costoCombustible = costoCombustible;
    }

    public int getId_configuracion() {
        return id_configuracion;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public double getDepreciacionPorKm() {
        return depreciacionPorKm;
    }

    public double getCostoCombustible() {
        return costoCombustible;
    }

    public void setId_configuracion(int id_configuracion) {
        this.id_configuracion = id_configuracion;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public void setDepreciacionPorKm(double depreciacionPorKm) {
        this.depreciacionPorKm = depreciacionPorKm;
    }

    public void setCostoCombustible(double costoCombustible) {
        this.costoCombustible = costoCombustible;
    }
}
