package Objetos;

public class RutaRegular {
    private int id_rutaRegular;
    private int id_sucursalInicio;
    private int id_sucursalDestino;
    private double distancia;
    private double precio;

    public RutaRegular(){

    }

    public RutaRegular(int id_rutaRegular, int id_sucursalInicio, int id_sucursalDestino, double distancia, double precio) {
        this.id_rutaRegular = id_rutaRegular;
        this.id_sucursalInicio = id_sucursalInicio;
        this.id_sucursalDestino = id_sucursalDestino;
        this.distancia = distancia;
        this.precio = precio;
    }

    public int getId_rutaRegular() {
        return id_rutaRegular;
    }

    public int getId_sucursalInicio() {
        return id_sucursalInicio;
    }

    public int getId_sucursalDestino() {
        return id_sucursalDestino;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId_rutaRegular(int id_rutaRegular) {
        this.id_rutaRegular = id_rutaRegular;
    }

    public void setId_sucursalInicio(int id_sucursalInicio) {
        this.id_sucursalInicio = id_sucursalInicio;
    }

    public void setId_sucursalDestino(int id_sucursalDestino) {
        this.id_sucursalDestino = id_sucursalDestino;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
