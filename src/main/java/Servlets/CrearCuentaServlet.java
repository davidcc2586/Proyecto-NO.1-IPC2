package Servlets;

import BaseDatos.UsuarioDB;
import Objetos.Enums.RolUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CrearCuentaServlet", value = "/CrearCuentaServlet")
public class CrearCuentaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RolUsuario rolUsuario = RolUsuario.VIAJERO;
        String rol = rolUsuario.name();
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dpi = request.getParameter("dpi");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        String nit = request.getParameter("nit");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String claveConfirmacion = request.getParameter("confirmarClave");

        if (nombre == null || nombre.trim().isEmpty() || apellido == null || apellido.trim().isEmpty() || dpi == null || dpi.trim().isEmpty() || telefono == null || telefono.trim().isEmpty() || direccion == null || direccion.trim().isEmpty() || correo == null || correo.trim().isEmpty() || nit == null || nit.trim().isEmpty() || usuario == null || usuario.trim().isEmpty() || clave == null || clave.trim().isEmpty()) {

            request.setAttribute("mensaje", "Debes llenar todos los campos.");
            request.getRequestDispatcher("javascript/crearCuenta.jsp").forward(request, response);
            return;
        }

        if (!clave.equals(claveConfirmacion)){
            request.setAttribute("mensaje", "La contraseña no coincide con la confirmación.");
            request.getRequestDispatcher("javascript/crearCuenta.jsp").forward(request, response);
            return;
        }

        try {
            UsuarioDB usuarioDB = new UsuarioDB();
            String elementoRepetido = usuarioDB.ingresarNuevoUsuario(usuario, clave, rol, nombre, apellido, dpi, telefono, direccion, correo, nit);

            switch (elementoRepetido){
                case "dpi":
                    request.setAttribute("mensaje", "Dpi existente.");
                    break;
                case "usuario":
                    request.setAttribute("mensaje", "Usuario Existente.");
                    break;
                case "correo":
                    request.setAttribute("mensaje", "Correo existente.");
                    break;
                case "nit":
                    request.setAttribute("mensaje", "Nit existente.");
                    break;
                default:
                    request.setAttribute("mensaje", "Cuenta creada con éxito.");
                    break;
            }
            request.getRequestDispatcher("/javascript/crearCuenta.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al crear cuenta, intente más tarde.");
            request.getRequestDispatcher("/javascript/crearCuenta.jsp").forward(request, response);
        }
    }
}