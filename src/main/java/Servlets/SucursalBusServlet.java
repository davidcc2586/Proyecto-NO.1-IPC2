package Servlets;

import BaseDatos.BusDB;
import BaseDatos.ChoferDB;
import Objetos.Bus;
import Objetos.Enums.EstadoViaje;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "SucursalBusServlet", value = "/SucursalBusServlet")
public class SucursalBusServlet extends HttpServlet {

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
                case "cambiarEstadoBus":
                    cambiarEstadoBus(request,response);
                    break;
                case "actualizarDatos":
                    actualizarDatos(request,response);
                    break;
                case "ingresarNuevoBus":
                    ingresarNuevoBus(request,response);
                    break;
                case "eliminarBus":
                    eliminarBus(request,response);
                    break;
            }
        }
    }

    private void cambiarEstadoBus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_bus = Integer.parseInt(request.getParameter("idBus"));
        String estado = request.getParameter("estado");
        String estadoActividad = request.getParameter("estadoActividad");

        if(estadoActividad.equalsIgnoreCase(EstadoViaje.LIBRE.name())){
            BusDB busDB = new BusDB();
            try {
                busDB.cambiarEstadoBus(id_bus, estado);
                request.setAttribute("mensajeInformativoEstado", "Estado de bus actualizado");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("mensajeInformativoEstado", "Error al actualizar el estado, intente nuevamente");
            }
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
        } else {
            request.setAttribute("mensajeInformativoEstado", "El bus seleccionado se encuentra programado o en un viaje, solo puede deshabilitarlo si se encuentra libre");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
        }
    }

    private void actualizarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idBusParam = request.getParameter("idBus");
        String parametroCantidadPasajero = request.getParameter("capacidadPasajeros");
        String parametroKilometraje = request.getParameter("kilometraje");

        if (idBusParam == null || parametroCantidadPasajero == null || parametroKilometraje == null || parametroCantidadPasajero.trim().isEmpty() || parametroKilometraje.trim().isEmpty()) {

            request.setAttribute("idBusActualizar", idBusParam);
            request.setAttribute("mensajeInformativoActualizacion", "Error, debe llenar todos los campos solicitados.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
            return;
        }

        StringBuilder nuevaCantidadPasajeros = filtrarNumerosEnteros(parametroCantidadPasajero);
        StringBuilder nuevoKilometraje = filtrarNumerosEnteros(parametroKilometraje);

        if (nuevaCantidadPasajeros == null || nuevoKilometraje == null) {
            request.setAttribute("idBusActualizar", idBusParam);
            request.setAttribute("mensajeInformativoActualizacion", "Error, indique únicamente números enteros.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
            return;
        }

        int id_bus = Integer.parseInt(idBusParam);
        int capacidad = Integer.parseInt(nuevaCantidadPasajeros.toString());
        int kilometraje = Integer.parseInt(nuevoKilometraje.toString());

        if (capacidad >= 50){
            request.setAttribute("idBusActualizar", idBusParam);
            request.setAttribute("mensajeInformativoActualizacion", "Error, la capacidad maxima de pasajeros en general es de 50.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
            return;
        }

        BusDB busDB = new BusDB();

        try {
            Optional<Bus> bus = busDB.obtenerBus(id_bus);
            if (!EstadoViaje.LIBRE.name().equalsIgnoreCase(bus.get().getEstadoActividad())){
                request.setAttribute("idBusActualizar", idBusParam);
                request.setAttribute("mensajeInformativoActualizacion", "El bus seleccionado se encuentra programado o en un viaje, solo puede actualizarlo si se encuentra libre");
                request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
                return;
            }

            busDB.actualizarDatosBus(id_bus, capacidad, kilometraje);
            request.setAttribute("mensajeInformativoActualizacion", "Datos del bus actualizados exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("idBusActualizar", idBusParam);
            request.setAttribute("mensajeInformativoActualizacion", "Error al actualizar en la base de datos.");
        }

        request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
    }

    private void ingresarNuevoBus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String numeroPlaca = request.getParameter("numeroPlaca");
        String año = request.getParameter("año");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String capacidad = request.getParameter("capacidad");
        String kilometraje = request.getParameter("kilometraje");
        String imagen = request.getParameter("imagen");
        int idSucursal = Integer.parseInt(request.getParameter("idSucursal"));

        if (numeroPlaca == null || año == null || marca == null || modelo == null || capacidad == null || kilometraje == null || imagen == null || numeroPlaca.trim().isEmpty() || año.trim().isEmpty() || marca.trim().isEmpty() || modelo.trim().isEmpty() || capacidad.trim().isEmpty() || kilometraje.trim().isEmpty() || imagen.trim().isEmpty()) {
            request.setAttribute("nuevoBus", "true");
            request.setAttribute("mensajeInformativoNuevoBus", "Error, debe llenar todos los campos solicitados.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
            return;
        }

        StringBuilder añoFiltrado = filtrarNumerosEnteros(año);
        StringBuilder capacidadFiltrad = filtrarNumerosEnteros(capacidad);
        StringBuilder kilometrajeFiltrado = filtrarNumerosEnteros(kilometraje);

        if (añoFiltrado == null || capacidadFiltrad == null || kilometrajeFiltrado == null){
            request.setAttribute("nuevoBus", "true");
            request.setAttribute("mensajeInformativoNuevoBus", "Error, los datos númericos tienen que ser enteros positivos.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
            return;
        }

        if (!(año.length() == 4)){
            request.setAttribute("nuevoBus", "true");
            request.setAttribute("mensajeInformativoNuevoBus", "Error, el año es inválido.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
            return;
        }

        int añoInt = Integer.parseInt(String.valueOf(añoFiltrado));
        int capacidadInt = Integer.parseInt(String.valueOf(capacidadFiltrad));
        int kilometrajeInt = Integer.parseInt(String.valueOf(kilometrajeFiltrado));

        if (capacidadInt >= 50){
            request.setAttribute("nuevoBus", "true");
            request.setAttribute("mensajeInformativoNuevoBus", "Error, la capacidad maxima de pasajeros en general es de 50.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
            return;
        }

        String directorioBase = System.getProperty("user.home") + File.separator + "Descargas";
        File archivoImagen = new File(directorioBase, imagen);

        if (!archivoImagen.exists()) {
            request.setAttribute("nuevoBus", "true");
            request.setAttribute("mensajeInformativoNuevoBus", "Error, la imagen no se encuentra o esta en otra carpeta que no es descargas.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
            return;
        }

        try {
            BusDB busDB = new BusDB();
            busDB.ingresarNuevoBus(idSucursal,imagen,numeroPlaca,marca,modelo,añoInt,capacidadInt,kilometrajeInt);
            request.setAttribute("mensajeInformativoNuevoBus", "Bus ingresado exitosamente.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
        }catch (SQLException e){
            request.setAttribute("mensajeInformativoNuevoBus", "Error, intente nuevamente.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
        }

    }

    public void eliminarBus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int idBus = Integer.parseInt(request.getParameter("idBus"));
        String estadoActividad = request.getParameter("estadoActividad");

        if (!EstadoViaje.LIBRE.name().equalsIgnoreCase(estadoActividad)){
            request.setAttribute("mensajeInformativoEstado", "El bus seleccionado se encuentra programado o en un viaje, solo puede eliminar si se encuentra libre");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
            return;
        }

        BusDB busDB = new BusDB();
        try {
            busDB.eliminarBus(idBus);
            request.setAttribute("mensajeInformativoEstado", "Bus eliminado con éxito");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeInformativoEstado", "Error al eliminar el bus, vuelva a intentarlo.");
            request.getRequestDispatcher("javascript/VentanaAdminSucursal/administrarBuses.jsp").forward(request, response);
        }
    }

    private StringBuilder filtrarNumerosEnteros(String entrada){
        if (entrada == null){
            return null;
        }
        StringBuilder filtrado = new StringBuilder();
        for (int i = 0; i < entrada.length(); i++) {
            char a  = entrada.charAt(i);
            if (esDigito(a)){
                filtrado.append(a);
            }else {
                return null;
            }
        }
        return filtrado;
    }

    private boolean esDigito(char c) {
        return c >= '0' && c <= '9';
    }

}
