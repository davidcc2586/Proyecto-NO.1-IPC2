package Objetos;

public class Usuario {
    private int id_usuario;
    private String usuario;
    private String rol;
    private String nombre;
    private String apellido;
    private String dpi;
    private String telefono;
    private String direccion;
    private String correo;
    private String nit;
    private double saldoCartera;


    public Usuario(){

    }

    public Usuario(int id_usuario, String usuario, String rol, String nombre, String apellido, String dpi, String telefono, String direccion, String correo, String nit, double saldoCartera) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dpi = dpi;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.nit = nit;
        this.saldoCartera = saldoCartera;
    }

    public String getRol() {
        return rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDpi() {
        return dpi;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNit() {
        return nit;
    }

    public double getSaldoCartera() {
        return saldoCartera;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setSaldoCartera(double saldoCartera) {
        this.saldoCartera = saldoCartera;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
