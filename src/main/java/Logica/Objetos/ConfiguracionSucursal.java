package Logica.Objetos;

public class ConfiguracionSucursal {
    private int id_sucursal;
    private double depreciacionPorKm;
    private double costoCombustible;


    public int getId_sucursal() {
        return id_sucursal;
    }

    public double getDepreciacionPorKm() {
        return depreciacionPorKm;
    }

    public double getCostoCombustible() {
        return costoCombustible;
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
