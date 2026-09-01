package Objetos;

import java.time.LocalDate;

public class Chofer {
    private int id_chofer;
    private String foto;
    private String nombre;
    private String apellido;
    private String licencia;
    private String tipoLicencia;
    private LocalDate vencimientoLicencia;
    private String telefono;
    private Double salarioBaseViaje;
    private String estadoActividad;
    private String estado;
    private int id_sucursal;

    public Chofer(){

    }

    public Chofer(int id_chofer, String foto, String nombre, String apellido, String licencia, String tipoLicencia, LocalDate vencimientoLicencia, String telefono, Double salarioBaseViaje, String estadoActividad, String estado, int id_sucursal) {
        this.id_chofer = id_chofer;
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.licencia = licencia;
        this.tipoLicencia = tipoLicencia;
        this.vencimientoLicencia = vencimientoLicencia;
        this.telefono = telefono;
        this.salarioBaseViaje = salarioBaseViaje;
        this.estadoActividad = estadoActividad;
        this.estado = estado;
        this.id_sucursal = id_sucursal;
    }

    public int getId_chofer() {
        return id_chofer;
    }

    public String getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getLicencia() {
        return licencia;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public LocalDate getVencimientoLicencia() {
        return vencimientoLicencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public Double getSalarioBaseViaje() {
        return salarioBaseViaje;
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

    public void setId_chofer(int id_chofer) {
        this.id_chofer = id_chofer;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public void setVencimientoLicencia(LocalDate vencimientoLicencia) {
        this.vencimientoLicencia = vencimientoLicencia;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setSalarioBaseViaje(Double salarioBaseViaje) {
        this.salarioBaseViaje = salarioBaseViaje;
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
