package Objetos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Boleto {
    private int id_boleto;
    private int id_usuario;
    private int id_viajeRegular;
    private int numeroAsiento;
    private double precio;
    private LocalDate fechaCompra;

    public Boleto(){

    }

    public Boleto(int id_boleto, int id_usuario, int id_viajeRegular, int numeroAsiento, double precio, LocalDate fechaCompra) {
        this.id_boleto = id_boleto;
        this.id_usuario = id_usuario;
        this.id_viajeRegular = id_viajeRegular;
        this.numeroAsiento = numeroAsiento;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
    }

    public int getId_boleto() {
        return id_boleto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public int getId_viajeRegular() {
        return id_viajeRegular;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setId_boleto(int id_boleto) {
        this.id_boleto = id_boleto;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setId_viajeRegular(int id_viajeRegular) {
        this.id_viajeRegular = id_viajeRegular;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}