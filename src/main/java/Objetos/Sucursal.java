package Objetos;

public class Sucursal{
    private int id_sucursal;
    private String departamento;
    private String municipio;
    private double latitud;
    private double longitud;
    private String direccion;
    private String telefono;
    private String correo;
    private String estado;
    /*
    private List<Usuario> usuarios;
    private List<Bus> buses;
    private List<Chofer> chofers;
    private List<RutaRegular> rutasRegulares;
    private List<RutaPrivada> rutasPrivadas;
    private List<ViajeRegular> viajesRegulares;
    private List<ViajeAlquiler> viajesAlquiler;
    private List<Boleto> boletos;

     */

    public Sucursal(int id_sucursal, String departamento, String municipio, double latitud, double longitud, String direccion, String telefono, String correo, String estado) {
        this.id_sucursal = id_sucursal;
        this.departamento = departamento;
        this.municipio = municipio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getEstado() {
        return estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
