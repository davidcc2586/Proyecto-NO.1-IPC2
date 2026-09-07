<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Objetos.*" %>
<%@ page import="BaseDatos.*" %>
<%@ page import="com.mysql.cj.protocol.a.SqlDateValueEncoder" %>
<%@ page import="Objetos.Enums.EstadoSolicitudViajePrivado" %><%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 2/9/26
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoletoDB boletoDB = new BoletoDB();
    ViajeRegularDB viajeRegularDB = new ViajeRegularDB();
    RutaRegularDB rutaRegularDB = new RutaRegularDB();
    SucursalDB sucursalDB = new SucursalDB();
    SolicitudViajePrivadoDB solicitudViajePrivadoDB = new SolicitudViajePrivadoDB();
%>
<html>
<head>
    <title>Historial de Viajes</title>
</head>
<body>

<h2>Registro de Viajes regulares</h2>

<table border="1">
    <tr>
        <th>NO. boleto</th>
        <th>Fecha de compra</th>
        <th>Sucursal inicio</th>
        <th>Sucursal destino</th>
        <th>Fecha salida</th>
        <th>Hora salida</th>
        <th>Numero asiento</th>
        <th>Precio</th>
    </tr>
    <%
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        try {
            List<Boleto> boletos = boletoDB.boletosUsuario(usuario.getId_usuario());
            if(boletos != null &&  !boletos.isEmpty()){
                for (Boleto boleto :  boletos){
                    ViajeRegular viajeRegular = viajeRegularDB.solicitarViajeRegular(boleto.getId_viajeRegular());
                    RutaRegular rutaRegular = rutaRegularDB.obtenerRutaRegular(viajeRegular.getId_rutaRegular());
                    int id_sucursalInicio = rutaRegular.getId_sucursalInicio();
                    int id_sucursalDestino = rutaRegular.getId_sucursalDestino();
                    Sucursal sucursalInicio = sucursalDB.obtenerSucursal(id_sucursalInicio);
                    Sucursal sucursalDestino = sucursalDB.obtenerSucursal(id_sucursalDestino);
    %>
                    <tr>
                        <td><%= boleto.getId_boleto() %></td>
                        <td><%= boleto.getFechaCompra() %></td>
                        <td><%= sucursalInicio.getDireccion() %></td>
                        <td><%= sucursalDestino.getDireccion() %></td>
                        <td><%= viajeRegular.getFechaSalida() %></td>
                        <td><%= viajeRegular.getHoraSalida() %></td>
                        <td><%= boleto.getNumeroAsiento()%></td>
                        <td><%= boleto.getPrecio() %></td>
                    </tr>
    <%
                }
            }else{
                    request.setAttribute("mensaje", "No cuentas con boletos de viajes regulares por el momento.");
                    request.getRequestDispatcher("javascript/VentanaCliente/historialViajes.jsp").forward(request, response);
            }
        } catch (SQLException e){
                    request.setAttribute("mensaje", "Error al procesar la solicitud, intente nuevamente.");
                    request.getRequestDispatcher("javascript/VentanaCliente/historialViajes.jsp").forward(request, response);
        }
    %>
</table>
<p>${mensaje}</p>


<h2>Registro de viajes privados</h2>



<h2>Solicitudes de viajes privados</h2>
<%
    try {
        List<SolicitudViajePrivado> solicitudesViajesPrivados = solicitudViajePrivadoDB.solicitudesUsuario(usuario.getId_usuario());
        if (solicitudesViajesPrivados != null && !solicitudesViajesPrivados.isEmpty()){
%>
<table border="1">
    <tr>
        <th>NO. solicitud</th>
        <th>cantidad pasajeros</th>
        <th>Sucursal de inicio</th>
        <th>Destino</th>
        <th>Fecha de salida</th>
        <th>Hora Salida</th>
        <th>Costo</th>
        <th>Estado solicitud</th>
        <th>Estado pago</th>
        <th>Acción</th>

    </tr>
    <%
        for (SolicitudViajePrivado solicitudViajePrivado : solicitudesViajesPrivados) {
            Sucursal sucursal = sucursalDB.obtenerSucursal(solicitudViajePrivado.getIdSucursal());
    %>
    <tr>
        <td><%= solicitudViajePrivado.getIdSolicitudViajePrivado() %></td>
        <td><%= solicitudViajePrivado.getCantidadPasajeros() %></td>
        <td><%= sucursal.getDireccion() %></td>
        <td><%= solicitudViajePrivado.getDireccionDestino() %></td>
        <td><%= solicitudViajePrivado.getFechaSalida() %></td>
        <td><%= solicitudViajePrivado.getHoraSalida() %></td>
        <td><%= solicitudViajePrivado.getCosto() %></td>
        <td><%= solicitudViajePrivado.getEstadoSolicitud() %></td>
        <td><%= solicitudViajePrivado.getEstadoPago() %></td>
        <td>
            <%
                if (solicitudViajePrivado.getEstadoSolicitud() != null && !solicitudViajePrivado.getEstadoPago().equalsIgnoreCase(EstadoSolicitudViajePrivado.CANCELADO.name()) && solicitudViajePrivado.getEstadoSolicitud().equalsIgnoreCase(EstadoSolicitudViajePrivado.ATENDIDA.name())) {
                    %>
            <form action="${pageContext.request.contextPath}/ClienteServlet" method="post" style="margin:0;">
                <input type="hidden" name="evento" value="pagarSolicitudViaje">
                <input type="hidden" name="costo" value="<%= solicitudViajePrivado.getCosto() %>">
                <input type="hidden" name="idSolicitud" value="<%= solicitudViajePrivado.getIdSolicitudViajePrivado() %>">
                <button type="submit">Pagar</button>
            </form>
            <%
                }
                %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
        }
    }catch (SQLException e){

    }
%>


<p>${mensaje}</p>

<form action="${pageContext.request.contextPath}/javascript/VentanaCliente/ventanaInicioCliente.jsp" method="GET">
    <p>
        <button type="submit">Regresar al inicio</button>
    </p>
</form>

</body>
</html>
