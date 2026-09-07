package Servlets;

import BaseDatos.*;
import Logica.Exception.LatitudLongitudException;
import Logica.Exception.SaldoInsuficienteException;
import Objetos.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet(name = "ClienteServlet", value = "/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String evento = request.getParameter("evento");
        if(evento != null) {
            switch (evento) {
                case "recargarSaldo":
                    recargarSaldo(request, response);
                    break;
                case "actualizarDatos":
                    actualizarDatos(request, response);
                    break;
                case "comprarBoleto":
                    compraBoleto(request,response);
                    break;
                case "filtrarDistanciaYCantidadPasajeros":
                    filtrarDistanciaYCantidadPasajeros(request,response);
                    break;
                case "solicitudAlquilarBus":
                    solicitudAlquilarBus(request,response);
                    break;
                case "pagarSolicitudViaje":
                    pagarSolicitudViaje(request, response);
                    break;
            }
        }
    }

    public void recargarSaldo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cantidad = request.getParameter("saldoAñadir");
        StringBuilder cantidadFiltrada = new StringBuilder("");
        boolean punto = false;
        boolean puntoDisponible = true;

        if(cantidad != null){
            for (int i = 0; i < cantidad.length() ; i++) {
                char a = cantidad.charAt(i);
                if(esDigito(a)){
                    cantidadFiltrada.append(a);
                    if (puntoDisponible) {
                        punto = true;
                        puntoDisponible = false;
                    }
                } else if (a == '.' && punto) {
                    punto = false;
                    cantidadFiltrada.append(a);
                } else{
                    cantidadFiltrada = null;
                    break;
                }
            }
        }

        if (cantidadFiltrada != null && cantidad != null){
            double cantidadFinal = Double.parseDouble(cantidadFiltrada.toString());
            UsuarioDB usuarioDB = new UsuarioDB();
            try {
                HttpSession sesion = request.getSession();
                Usuario usuario = (Usuario) sesion.getAttribute("usuario");
                usuario.setSaldoCartera(usuario.getSaldoCartera() + cantidadFinal);
                request.getSession().setAttribute("usuario", usuario);
                usuarioDB.agregarSaldoCartera(usuario.getId_usuario(), cantidadFinal);
                request.setAttribute("mensaje", "Cantidad añadida con exito.");
                request.getRequestDispatcher("javascript/VentanaCliente/recargarSaldo.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("mensaje", "Error al añadir cantidad, intente más tarde.");
                request.getRequestDispatcher("javascript/VentanaCliente/recargarSaldo.jsp").forward(request,response);
            }
        } else {
            request.setAttribute("mensaje", "Error, la cantidad no es valida.");
            request.getRequestDispatcher("javascript/VentanaCliente/recargarSaldo.jsp").forward(request,response);
        }
    }

    private boolean esDigito(char c) {
        return c >= '0' && c <= '9';
    }


    public void actualizarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        String nuevoUsuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String claveConfirmacion = request.getParameter("confirmarClave");
        UsuarioDB usuarioDB = new UsuarioDB();

        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        try {
            if (clave == null || clave.isEmpty() || claveConfirmacion == null || claveConfirmacion.isEmpty()){
                usuarioDB.actualizarDatos(usuario.getId_usuario(), telefono,direccion,correo, nuevoUsuario);
                usuario.setTelefono(telefono);
                usuario.setDireccion(direccion);
                usuario.setCorreo(correo);
                usuario.setUsuario(nuevoUsuario);
                request.setAttribute("mensaje", "Datos actualizados con exito.");
                request.getRequestDispatcher("/javascript/VentanaCliente/actualizarDatos.jsp").forward(request, response);
            } else if (clave != null && !clave.isEmpty() && claveConfirmacion != null && !claveConfirmacion.isEmpty()) {
                if (!clave.equals(claveConfirmacion)){
                    request.setAttribute("mensaje", "La contraseña no coincide con la confirmación.");
                    request.getRequestDispatcher("/javascript/VentanaCliente/actualizarDatos.jsp").forward(request, response);
                }else{
                    usuarioDB.actualizarDatos(usuario.getId_usuario(), telefono,direccion,correo, nuevoUsuario, clave);
                    usuario.setTelefono(telefono);
                    usuario.setDireccion(direccion);
                    usuario.setCorreo(correo);
                    usuario.setUsuario(nuevoUsuario);
                    request.setAttribute("mensaje", "Datos actualizados con exito.");
                    request.getRequestDispatcher("/javascript/VentanaCliente/actualizarDatos.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al actualizar datos, intente más tarde.");
            request.getRequestDispatcher("/javascript/VentanaCliente/actualizarDatos.jsp").forward(request, response);
        }
    }

    public void compraBoleto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idSucursalParam = request.getParameter("idSucursal");
        String idViajeParam = request.getParameter("numeroViaje");

        String fechaIngresada = request.getParameter("fechaCompra");
        String numeroAsientoStr = request.getParameter("numeroAsiento");

        try {
            LocalDate fechaCompra = LocalDate.parse(fechaIngresada);
            int id_viajeRegular = Integer.parseInt(idViajeParam);
            int numeroAsiento = Integer.parseInt(numeroAsientoStr);

            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            BoletoDB boletoDB = new BoletoDB();
            ViajeRegularDB viajeRegularDB = new ViajeRegularDB();
            ViajeRegular viajeRegular = viajeRegularDB.solicitarViajeRegular(id_viajeRegular);
            RutaRegularDB rutaRegularDB = new RutaRegularDB();
            RutaRegular rutaRegular = rutaRegularDB.obtenerRutaRegular(viajeRegular.getId_rutaRegular());
            boletoDB.registrarNuevoBoleto(usuario.getId_usuario(), id_viajeRegular, numeroAsiento, rutaRegular.getPrecio(), fechaCompra, viajeRegular.getId_bus());
            usuario.setSaldoCartera(usuario.getSaldoCartera() - rutaRegular.getPrecio());
            request.setAttribute("mensaje", "Compra de boleto exitoso.");
        } catch (DateTimeException ex) {
            request.setAttribute("mensaje", "Formato de fecha inválido.");
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "Datos numéricos inválidos.");
        } catch (SQLException e) {
            request.setAttribute("mensaje", "Error al procesar la solicitud, intente nuevamente.");
        } catch (SaldoInsuficienteException e) {
            request.setAttribute("mensaje", "Saldo insuficiente.");
        }

        request.getRequestDispatcher("/javascript/VentanaCliente/comprarBoleto.jsp?idSucursal=" + idSucursalParam + "&idViaje=" + idViajeParam).forward(request, response);
    }

    public void filtrarDistanciaYCantidadPasajeros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cantidadPasajeros = request.getParameter("pasajeros");
        String distancia = request.getParameter("distancia");
        if(cantidadPasajeros != null && !cantidadPasajeros.isEmpty() && distancia != null && !distancia.isEmpty()) {
            StringBuilder distanciaFiltrada = new StringBuilder("");
            StringBuilder cantidadPasajerosFiltrada = new StringBuilder("");

            boolean puntoUsado = false;
            boolean distanciaValida = true;

            for (int i = 0; i < distancia.length(); i++) {
                char a = distancia.charAt(i);
                if (esDigito(a)) {
                    distanciaFiltrada.append(a);
                } else if (a == '.' && !puntoUsado && distanciaFiltrada.length() > 0) {
                    puntoUsado = true;
                    distanciaFiltrada.append(a);
                } else {
                    distanciaValida = false;
                    break;
                }
            }

            boolean pasajerosValidos = true;
            for (int i = 0; i < cantidadPasajeros.length(); i++) {
                char a = cantidadPasajeros.charAt(i);
                if (esDigito(a)) {
                    cantidadPasajerosFiltrada.append(a);
                } else {
                    pasajerosValidos = false;
                    break;
                }
            }

            if (!distanciaValida || !pasajerosValidos || distanciaFiltrada.length() == 0 || cantidadPasajerosFiltrada.length() == 0) {
                request.setAttribute("mensaje", "Datos numéricos inválidos.");
                request.getRequestDispatcher("/javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);
            } else {
                String paso = "2";
                request.setAttribute("distancia", distanciaFiltrada);
                request.setAttribute("pasajeros", cantidadPasajerosFiltrada);
                request.setAttribute("paso", paso);
                request.getRequestDispatcher("/javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);
            }
        }else {
            request.setAttribute("mensaje", "Llenar todos los campos.");
            request.getRequestDispatcher("/javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);
        }
    }

    public void solicitudAlquilarBus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolicitudViajePrivadoDB solicitudViajePrivadoDB = new SolicitudViajePrivadoDB();

        String pasajerosStr = request.getParameter("pasajeros");
        String distanciaStr = request.getParameter("distancia");
        String costoStr = request.getParameter("costo");

        if (pasajerosStr == null) {
            pasajerosStr = String.valueOf(request.getAttribute("pasajeros"));
        }
        if (distanciaStr == null) {
            distanciaStr = String.valueOf(request.getAttribute("distancia"));
        }

        try {
            int cantidadPasajeros = Integer.parseInt(pasajerosStr);
            double distanciaIda = Double.parseDouble(distanciaStr);
            double costo = Double.parseDouble(costoStr);

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            int idUsuario = usuario.getId_usuario();
            int idSucursal = Integer.parseInt(request.getParameter("idSucursal"));

            String destino = request.getParameter("destino");
            String latitud = request.getParameter("latitud");
            String longitud = request.getParameter("longitud");
            String fechaSalida = request.getParameter("fechaSalida");
            String horaSalida = request.getParameter("horaSalida");
            String fechaRegreso = request.getParameter("fechaRegreso");
            String horaRegreso = request.getParameter("horaRegreso");

            if (destino == null || destino.isEmpty() || latitud == null || latitud.isEmpty() || longitud == null || longitud.isEmpty() || fechaSalida == null || fechaSalida.isEmpty() || horaSalida == null || horaSalida.isEmpty() || fechaRegreso == null || fechaRegreso.isEmpty() || horaRegreso == null || horaRegreso.isEmpty()){
                request.setAttribute("distancia", distanciaIda);
                request.setAttribute("pasajeros", cantidadPasajeros);
                request.setAttribute("paso", "2");
                request.setAttribute("mensaje", "Llenar todos los campos.");
                request.getRequestDispatcher("/javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);
                return;
            }

            String latitudString = esLatitudYLongitud(latitud);
            String longitudString = esLatitudYLongitud(longitud);
            if (latitudString == null || longitudString == null) {
                throw new LatitudLongitudException();
            }

            double latitudFinal = Double.parseDouble(latitudString);
            double longitudFinal = Double.parseDouble(longitudString);
            LocalDate fechaSalidaFinal = LocalDate.parse(fechaSalida);
            LocalDate fechaRegresoFinal = LocalDate.parse(fechaRegreso);
            LocalTime horaSalidaFinal = LocalTime.parse(horaSalida);
            LocalTime horaRegresoFinal = LocalTime.parse(horaRegreso);

            solicitudViajePrivadoDB.ingresarNuevaSolicitud(cantidadPasajeros, distanciaIda, idUsuario, idSucursal, destino, latitudFinal, longitudFinal, fechaSalidaFinal, horaSalidaFinal, fechaRegresoFinal, horaRegresoFinal, costo);
            request.setAttribute("paso", "3");
            request.getRequestDispatcher("/javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);

        } catch (NumberFormatException ex) {
            request.setAttribute("paso", "2");
            request.setAttribute("mensaje", "Error en los datos numéricos enviados.");
            request.getRequestDispatcher("/javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);
        } catch (DateTimeException ex) {
            request.setAttribute("paso", "2");
            request.setAttribute("mensaje", "Formato de fecha/Hora incorrecta");
            request.getRequestDispatcher("/javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);
        } catch (LatitudLongitudException e) {
            request.setAttribute("paso", "2");
            request.setAttribute("mensaje", "Latitud/longitud incorrecta");
            request.getRequestDispatcher("/javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("paso", "2");
            request.setAttribute("mensaje", "Error al enviar la solicitud, vuelva a intentarlo");
            request.getRequestDispatcher("/javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);
        }
    }


    public void pagarSolicitudViaje(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
        double costo = Double.parseDouble(request.getParameter("costo"));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        UsuarioDB usuarioDB = new UsuarioDB();
        if (usuario.getSaldoCartera() < costo){
            request.setAttribute("mensaje", "Saldo insuficiente para completar el pago.");
            request.getRequestDispatcher("/javascript/VentanaCliente/historialViajes.jsp").forward(request, response);
        } else {
            try {
                usuarioDB.pagarSolicitudViaje(usuario.getId_usuario(),idSolicitud,costo);
                usuario.setSaldoCartera(usuario.getSaldoCartera() - costo);
                request.setAttribute("mensaje", "Pago realizado con exito.");
                request.getRequestDispatcher("/javascript/VentanaCliente/historialViajes.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("mensaje", "Error al hacer el pago, intente nuevamente.");
                request.getRequestDispatcher("/javascript/VentanaCliente/historialViajes.jsp").forward(request, response);
            }

        }
    }

    public String esLatitudYLongitud(String entrada){
        StringBuilder entradaFiltrada = new StringBuilder();

        boolean punto = false;
        boolean puntoDisponible = true;
        if(entrada != null){
            for (int i = 0; i < entrada.length() ; i++) {
                char a = entrada.charAt(i);
                if(esDigito(a)){
                    entradaFiltrada.append(a);
                    if (puntoDisponible) {
                        punto = true;
                        puntoDisponible = false;
                    }
                } else if (a == '.' && punto) {
                    punto = false;
                    entradaFiltrada.append(a);
                } else if (a == '-' && i == 0) {
                    entradaFiltrada.append(a);
                } else{
                    return null;
                }
            }
            return entradaFiltrada.toString();
        }else {
           return null;
        }
    }

}
