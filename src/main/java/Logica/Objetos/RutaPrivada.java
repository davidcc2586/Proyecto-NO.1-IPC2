package Logica.Objetos;

public class RutaPrivada {
    private int id_rutaPrivada;
    private double latitudA;
    private double longitudA;
    private String direccionA;
    private double latitudB;
    private double longitudB;
    private String direccionB;
    private double distancia;
    private double precio;

    public RutaPrivada(){

    }

    public RutaPrivada(int id_rutaPrivada, double latitudA, double longitudA, String direccionA, double latitudB, double longitudB, String direccionB, double distancia, double precio) {
        this.id_rutaPrivada = id_rutaPrivada;
        this.latitudA = latitudA;
        this.longitudA = longitudA;
        this.direccionA = direccionA;
        this.latitudB = latitudB;
        this.longitudB = longitudB;
        this.direccionB = direccionB;
        this.distancia = distancia;
        this.precio = precio;
    }

    public int getId_rutaPrivada() {
        return id_rutaPrivada;
    }

    public double getLatitudA() {
        return latitudA;
    }

    public double getLongitudA() {
        return longitudA;
    }

    public String getDireccionA() {
        return direccionA;
    }

    public double getLatitudB() {
        return latitudB;
    }

    public double getLongitudB() {
        return longitudB;
    }

    public String getDireccionB() {
        return direccionB;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId_rutaPrivada(int id_rutaPrivada) {
        this.id_rutaPrivada = id_rutaPrivada;
    }

    public void setLatitudA(double latitudA) {
        this.latitudA = latitudA;
    }

    public void setLongitudA(double longitudA) {
        this.longitudA = longitudA;
    }

    public void setDireccionA(String direccionA) {
        this.direccionA = direccionA;
    }

    public void setLatitudB(double latitudB) {
        this.latitudB = latitudB;
    }

    public void setLongitudB(double longitudB) {
        this.longitudB = longitudB;
    }

    public void setDireccionB(String direccionB) {
        this.direccionB = direccionB;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
