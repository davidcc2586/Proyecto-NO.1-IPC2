package Servlets;

import BaseDatos.ChoferDB;
import Objetos.Enums.Estado;
import Objetos.Enums.EstadoViaje;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;

@WebServlet(name = "SucursalChoferServlet", value = "/SucursalChoferServlet")
public class SucursalChoferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String evento = request.getParameter("evento");
        if (evento != null){
            switch (evento){
                case "cambiarEstadoChofer":
                    cambiarEstadoChofer(request,response);
                    break;
                case "eliminarChofer":
                    eliminarChofer(request,response);
                    break;
                case "actualizarDatos":
                    actualizarDatos(request,response);
                    break;
                case "ingresarNuevoChofer":
                    ingresarNuevoChofer(request,response);
                    break;
            }
        }
    }

    public void cambiarEstadoChofer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_chofer = Integer.parseInt(request.getParameter("idChofer"));
        String estado = request.getParameter("estado");
        String estadoActividad = request.getParameter("estadoActividad");

        if(estadoActividad.equalsIgnoreCase(EstadoViaje.LIBRE.name())){
            ChoferDB choferDB = new ChoferDB();
            try {
                choferDB.cambiarEstadoChofer(id_chofer, estado);
                request.setAttribute("mensajeInformativoEstado", "Estado del chofer actualizado");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("mensajeInformativoEstado", "Error al actualizar el estado, intente nuevamente");
            }
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
        } else {
            request.setAttribute("mensajeInformativoEstado", "El chofer seleccionado se encuentra programado o en un viaje, solo puede deshabilitarlo si se encuentra libre");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
        }
    }

    public void eliminarChofer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idChofer = Integer.parseInt(request.getParameter("idChofer"));
        String estadoActividad = request.getParameter("estadoActividad");

        if (!EstadoViaje.LIBRE.name().equalsIgnoreCase(estadoActividad)){
            request.setAttribute("mensajeInformativoEstado", "El chofer seleccionado se encuentra programado o en un viaje, solo puede eliminar si se encuentra libre");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
            return;
        }

        ChoferDB choferDB = new ChoferDB();
        try {
            choferDB.eliminarChofer(idChofer);
            request.setAttribute("mensajeInformativoEstado", "Chofer eliminado con éxito");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeInformativoEstado", "Error al eliminar chofer, vuelva a intentarlo.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
        }
    }

    public void actualizarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int idChofer = Integer.parseInt(request.getParameter("idChofer"));
        String vencimientoLicencia = request.getParameter("vencimientoLicencia");
        String telefono = request.getParameter("telefono");
        String salarioViaje = request.getParameter("salarioViaje");

        if (!EstadoViaje.LIBRE.name().equalsIgnoreCase(request.getParameter("estadoActividad"))){
            request.setAttribute("mensajeInformativoActualizacion", "Error, solo puede actualizar datos cuando el chofer se encuentre libre.");
            request.setAttribute("idChoferActualizar", idChofer);
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
            return;
        }

        if(vencimientoLicencia == null ||  telefono == null || salarioViaje == null ||  vencimientoLicencia.trim().isEmpty() || telefono.trim().isEmpty() || salarioViaje.trim().isEmpty()){
            request.setAttribute("mensajeInformativoActualizacion", "Error, llene todos lo campos solicitados.");
            request.setAttribute("idChoferActualizar", idChofer);
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
            return;
        }

        if (telefono.length() != 8){
            request.setAttribute("mensajeInformativoActualizacion", "Error, número de teléfono invalido.");
            request.setAttribute("idChoferActualizar", idChofer);
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
            return;
        }


        try {
            LocalDate fechaFinal = LocalDate.parse(vencimientoLicencia);
            int telefonoFinal = Integer.parseInt(telefono);
            double salarioFinal = Double.parseDouble(salarioViaje);

            ChoferDB choferDB = new ChoferDB();
            choferDB.actualizarChofer(idChofer,fechaFinal,String.valueOf(telefonoFinal),salarioFinal);

            request.setAttribute("mensajeInformativoActualizacion", "Datos actualizados con exito.");
        } catch (DateTimeException ex) {
            request.setAttribute("idChoferActualizar", idChofer);
            request.setAttribute("mensajeInformativoActualizacion", "Error, formato de fecha inválido.");
        } catch (NumberFormatException ex) {
            request.setAttribute("idChoferActualizar", idChofer);
            request.setAttribute("mensajeInformativoActualizacion", "Error, datos numéricos incorrectos.");
        } catch (SQLException e) {
            request.setAttribute("idChoferActualizar", idChofer);
            request.setAttribute("mensajeInformativoActualizacion", "Error al procesar la solicitud, intente nuevamente.");
        }
        request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
    }

    public void ingresarNuevoChofer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_sucursal = Integer.parseInt(request.getParameter("idSucursal"));
        String foto = request.getParameter("imagen");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String licencia = request.getParameter("licencia");
        String tipoLicencia = request.getParameter("tipoLicencia");
        String vencimientoLicencia = request.getParameter("vencimientoLicencia");
        String telefono = request.getParameter("telefono");
        String salarioBaseViaje = request.getParameter("salarioBaseViaje");
        if(foto == null || foto.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || apellido == null || apellido.trim().isEmpty() || licencia == null || licencia.trim().isEmpty() || tipoLicencia == null || tipoLicencia.trim().isEmpty() || vencimientoLicencia == null || vencimientoLicencia.trim().isEmpty() || telefono == null || telefono.trim().isEmpty() || salarioBaseViaje == null || salarioBaseViaje.trim().isEmpty()){
            request.setAttribute("nuevoChofer", true);
            request.setAttribute("mensajeInformativoNuevochofer", "Error, debe llenar todos los campos solicitados.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
            return;
        }

        if (!tipoLicencia.equalsIgnoreCase("A")){
            request.setAttribute("nuevoChofer", true);
            request.setAttribute("mensajeInformativoNuevochofer", "Error, la licencia requerida es de tipo 'A'.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
            return;
        }

        if (!(telefono.length() == 8)){
            request.setAttribute("nuevoChofer", true);
            request.setAttribute("mensajeInformativoNuevochofer", "Error, teléfono inválido.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
            return;
        }

        try {
            LocalDate fechaFinal = LocalDate.parse(vencimientoLicencia);
            int telefonoFinal = Integer.parseInt(telefono);
            ChoferDB choferDB = new ChoferDB();
            double salarioBaseViajeFinal = Double.parseDouble(salarioBaseViaje);
            choferDB.ingresarNuevoChofer(id_sucursal, foto,nombre,apellido,licencia,tipoLicencia,fechaFinal,String.valueOf(telefonoFinal),salarioBaseViajeFinal);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DateTimeException ex) {
            request.setAttribute("nuevoChofer", true);
            request.setAttribute("mensajeInformativoNuevochofer", "Error, formato de fecha inválido.");
        }catch (NumberFormatException ex) {
            request.setAttribute("nuevoChofer", true);
            request.setAttribute("mensajeInformativoNuevochofer", "Error, datos numéricos inválido.");
        }
        request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarEmpleados.jsp").forward(request, response);
    }
}
