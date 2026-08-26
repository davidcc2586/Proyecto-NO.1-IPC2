package Logica.Objetos;

import java.awt.*;

public class Bus {

    private int id_bus;
    private Image imagen;
    private String numeroPlaca;
    private String marca;
    private String modelo;
    private String añoFabricacion;
    private int capacidadPasajeros;
    private int kilometrajeActual;
    private String estadoActividad;
    private String estado;
    private int id_sucursal;

    public Bus(){

    }

    public Bus(int id_bus, Image imagen, String numeroPlaca, String marca, String modelo, String añoFabricacion, int capacidadPasajeros, int kilometrajeActual, String estadoActividad, String estado, int id_sucursal) {
        this.id_bus = id_bus;
        this.imagen = imagen;
        this.numeroPlaca = numeroPlaca;
        this.marca = marca;
        this.modelo = modelo;
        this.añoFabricacion = añoFabricacion;
        this.capacidadPasajeros = capacidadPasajeros;
        this.kilometrajeActual = kilometrajeActual;
        this.estadoActividad = estadoActividad;
        this.estado = estado;
        this.id_sucursal = id_sucursal;
    }


    public int getId_bus() {
        return id_bus;
    }

    public Image getImagen() {
        return imagen;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAñoFabricacion() {
        return añoFabricacion;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public int getKilometrajeActual() {
        return kilometrajeActual;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public String getEstado() {
        return estado;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAñoFabricacion(String añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public void setKilometrajeActual(int kilometrajeActual) {
        this.kilometrajeActual = kilometrajeActual;
    }

    public void setEstadoActividad(String estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }
}
