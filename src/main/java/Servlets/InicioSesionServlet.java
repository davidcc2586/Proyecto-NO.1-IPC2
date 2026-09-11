package Servlets;

import BaseDatos.UsuarioDB;
import Objetos.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "InicioSesionServlet", value = "/InicioSesionServlet")
public class InicioSesionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuarioIngresado = request.getParameter("usuario");
        String claveIngresado = request.getParameter("clave");

        if (usuarioIngresado.isEmpty() ||  claveIngresado.isEmpty()) {
            request.setAttribute("mensaje", "Debes llenar todos los campos.");
            request.getRequestDispatcher("javascript/inicioSesion.jsp").forward(request, response);
            return;
        }

        try {

            Optional<Usuario> usuario = new UsuarioDB().inicioSesionUsuario(usuarioIngresado, claveIngresado);

            if (usuario.isPresent()) {
                request.getSession().setAttribute("usuario", usuario.get());
                switch (usuario.get().getRol()) {
                    case "VIAJERO":
                        request.getRequestDispatcher("/javascript/VentanaCliente/ventanaInicioCliente.jsp").forward(request, response);
                        break;
                    case "ADMIN_SISTEMA":

                        break;
                    case "ADMIN_SUCURSAL":
                        request.getRequestDispatcher("/javascript/VentanaAdminSucursal/ventanaInicioAdminSucursal.jsp").forward(request, response);
                        break;
                }
            } else {
                request.setAttribute("mensaje", "Usuario no encontrado.");
                request.getRequestDispatcher("javascript/inicioSesion.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            response.getWriter().write("error");
        }
    }
}
