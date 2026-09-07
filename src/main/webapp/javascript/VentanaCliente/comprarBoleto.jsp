<%@ page import="java.util.List" %>
<%@ page import="BaseDatos.SucursalDB" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="BaseDatos.ViajeRegularDB" %>
<%@ page import="BaseDatos.RutaRegularDB" %>
<%@ page import="BaseDatos.AsientoDB" %>
<%@ page import="Objetos.*" %>
<%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 1/9/26
  Time: 14:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ViajeRegularDB viajeRegularDB = new ViajeRegularDB();
    RutaRegularDB rutaRegularDB = new RutaRegularDB();
    SucursalDB sucursalDB = new SucursalDB();
    AsientoDB asientoDB = new AsientoDB();

    String idSucursalParam = request.getParameter("idSucursal");
    String idViajeParam = request.getParameter("idViaje");
    String idRutaParam = request.getParameter("idRuta");
%>
<html>
<head>
    <title>Comprar boletos</title>
</head>
<body>

<h2>Comprar boleto</h2>

<div>
    <h3>Seleccionar Sucursal</h3>
    <p>Para comprar un boleto, seleccione la ubicación de la sucursal donde desea iniciar el viaje</p>

    <%
        try {
            List<Sucursal> sucursales = sucursalDB.sucursalesActiva();
            if (sucursales == null || sucursales.isEmpty()) {
    %>
    <p>No existe ninguna sucursal hábil en este momento, ¡gracias por preferirnos!</p>
    <%
    } else {
    %>
    <form action="${pageContext.request.contextPath}/javascript/VentanaCliente/comprarBoleto.jsp" method="GET">
        <label for="idSucursal">Sucursales disponibles:</label>
        <select id="idSucursal" name="idSucursal">
            <%
                for (Sucursal sucursal : sucursales) {
            %>
            <option value="<%= sucursal.getId_sucursal() %>"><%= sucursal.getDireccion() %></option>
            <%
                }
            %>
        </select>

        <p>
            <button type="submit">Consultar viajes disponibles</button>
        </p>
    </form>
    <%
        }
    } catch (SQLException e) {
        e.printStackTrace();
    %>
    <p>Error al cargar los datos, reinicie la página.</p>
    <%
        }
    %>

    <%
        if (idSucursalParam != null && !idSucursalParam.isEmpty()) {
            int idSucursalSeleccionada = Integer.parseInt(idSucursalParam);
    %>

    <h3>Viajes disponibles para la sucursal NO.: <%= idSucursalSeleccionada %></h3>
    <br>

    <%
        try {
            List<ViajeRegular> viajeRegulares = viajeRegularDB.viajesRegularesDisponibles(idSucursalSeleccionada);
            if (viajeRegulares == null || viajeRegulares.isEmpty()) {
    %>
    <p>Esta sucursal no cuenta con rutas regulares disponibles en este momento.</p>
    <%
    } else {
    %>
    <table border="1">
        <tr>
            <th>No. viaje</th>
            <th>Inicio</th>
            <th>Destino</th>
            <th>Fecha salida</th>
            <th>Hora salida</th>
            <th>Fecha llegada</th>
            <th>Hora llegada</th>
            <th>Pasajeros</th>
            <th>Precio</th>
            <th>Comprar</th>
        </tr>

        <%
            for (ViajeRegular viaje : viajeRegulares) {
                RutaRegular rutaRegular = rutaRegularDB.obtenerRutaRegular(viaje.getId_rutaRegular());
                String inicio = "";
                String destino = "";
                double precio = 0.0;

                if (rutaRegular != null) {
                    precio = rutaRegular.getPrecio();
                    Sucursal sucInicio = sucursalDB.obtenerSucursal(rutaRegular.getId_sucursalInicio());
                    Sucursal sucDestino = sucursalDB.obtenerSucursal(rutaRegular.getId_sucursalDestino());
                    if (sucInicio != null) {
                        inicio = sucInicio.getDireccion();
                    }
                    if (sucDestino != null) {
                        destino = sucDestino.getDireccion();
                    }
                }
        %>
        <tr>
            <td><%= viaje.getId_viajeRegular() %></td>
            <td><%= inicio %></td>
            <td><%= destino %></td>
            <td><%= viaje.getFechaSalida() %></td>
            <td><%= viaje.getHoraSalida() %></td>
            <td><%= viaje.getFechaEstimadaLlegada() %></td>
            <td><%= viaje.getHoraEstimadaLlegada() %></td>
            <td><%= viaje.getCantidadPasajeros() %></td>
            <td><%= precio %></td>
            <td>
                <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/javascript/VentanaCliente/comprarBoleto.jsp?idSucursal=<%= idSucursalSeleccionada %>&idViaje=<%= viaje.getId_viajeRegular() %>&idRuta=<%= viaje.getId_rutaRegular() %>'">Seleccionar</button>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <%
        }
    } catch (SQLException e) {
        e.printStackTrace();
    %>
    <p>Error al cargar los viajes, intente nuevamente.</p>
    <%
            }
        }
    %>

    <%
        if (idViajeParam != null && !idViajeParam.isEmpty()) {
    %>
    <div>
        <h3>Compra de boleto</h3>
        <form action="${pageContext.request.contextPath}/ClienteServlet" method="post" id="formCompra">
            <input type="hidden" name="evento" value="comprarBoleto">
            <input type="hidden" name="idSucursal" value="<%= idSucursalParam %>">
            <p>
                <label for="numeroViaje">Número de viaje:</label>
                <input type="text" id="numeroViaje" name="numeroViaje" value="<%= idViajeParam %>" readonly>
            </p>
            <p>
                <label for="fechaCompra">Fecha de compra:</label>
                <input type="text" id="fechaCompra" name="fechaCompra" placeholder="YYYY-MM-DD">
            </p>
            <p>
                <label for="numeroAsiento">NO.asiento:</label>
                <select id="numeroAsiento" name="numeroAsiento">
                    <%
                        try {
                            int idViajeInt = Integer.parseInt(idViajeParam);
                            ViajeRegular viajeSel = viajeRegularDB.solicitarViajeRegular(idViajeInt);
                            if (viajeSel != null) {
                                int idBus = viajeSel.getId_bus();
                                List<Asiento> asientosBus = asientoDB.obtenerAsientosDisponibles(idBus);
                                if (asientosBus != null && !asientosBus.isEmpty()) {
                                    for (Asiento asiento : asientosBus) {
                    %>
                    <option value="<%= asiento.getNumero_asiento() %>"><%= asiento.getNumero_asiento() %></option>
                    <%
                        }
                    } else {
                    %>
                    <option value="">No hay asientos disponibles</option>
                    <%
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>
                </select>
            </p>
            <p>Nota: verificar la cantidad y número de viaje a pagar.</p>
            <p>${mensaje}</p>
            <button type="submit" id="botonPagar">Pagar</button>
        </form>
    </div>
    <%
        }
    %>

    <form action="${pageContext.request.contextPath}/javascript/VentanaCliente/ventanaInicioCliente.jsp" method="GET">
        <p>
            <button type="submit">Regresar al inicio</button>
        </p>
    </form>

</div>
</body>
</html>