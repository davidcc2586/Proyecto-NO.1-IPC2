<%@ page import="Objetos.Sucursal" %>
<%@ page import="java.util.List" %>
<%@ page import="BaseDatos.SucursalDB" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 2/9/26
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SucursalDB sucursalDB = new SucursalDB();
    String cantidadPasajerosParametro = request.getParameter("pasajeros");
    String distanciaParametro = request.getParameter("distancia");
    String numeroPaso = (String) request.getAttribute("paso");
%>
<html>
<head>
    <title>Alquilar bus</title>
</head>
<body>
<h1>Alquiler de buses</h1>
<p>Para alquilar un bus debera llenar un formulario con los datos necesarios para que la sucursal seleccionada apruebe su solicitud. </p>
<h2>formulario: </h2>

<%
    if (numeroPaso == null) {
%>
<form action= "${pageContext.request.contextPath}/ClienteServlet" method="post">
    <input type="hidden" name="evento" value="filtrarDistanciaYCantidadPasajeros">
    <p>
        <label for="pasajeros">Cantidad de pasajeros</label>
        <input type="text" name="pasajeros" id="pasajeros">
    </p>

    <p>
        <label for="distancia">Distancia Aproximada</label>
        <input type="text" name="distancia" id="distancia">
    </p>

    <button type="submit">Siguiente</button>
</form>
<%
    }else if (numeroPaso.equalsIgnoreCase("2") && cantidadPasajerosParametro != null && distanciaParametro != null){
%>
<form action="${pageContext.request.contextPath}/ClienteServlet" method="post">
    <input type="hidden" name="evento" value="solicitudAlquilarBus">
    <input type="hidden" name="pasajeros" value="<%= cantidadPasajerosParametro %>">
    <input type="hidden" name="distancia" value="<%= distanciaParametro %>">
    <input type="hidden" name="costo" value="<%=costoTotal(Integer.parseInt(cantidadPasajerosParametro), Double.parseDouble(distanciaParametro))%>">

    <p>
        <label for="idSucursal">Sucursales disponibles para inicio:</label>
        <select id="idSucursal" name="idSucursal">
            <%
                try {
                    List<Sucursal> sucursales = sucursalDB.sucursalesActiva();
                    if(sucursales != null &&  !sucursales.isEmpty()){
                        for(Sucursal sucursal: sucursales){
            %>
            <option value="<%= sucursal.getId_sucursal() %>"><%= sucursal.getDireccion() %></option>
            <%
                }
            %>
        </select>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("mensaje", "Error al procesar la solicitud, intente nuevamente.");
                request.getRequestDispatcher("javascript/VentanaCliente/alquilarBus.jsp").forward(request, response);
            }
        %>
    </p>

    <p>
        <label for="destino">Nombre del destino</label>
        <input type="text" name="destino" id="destino">
    </p>

    <p>
        <label for="latitud">Latitud</label>
        <input type="text" name="latitud" id="latitud">
    </p>
    <p>
        <label for="longitud">Longitud</label>
        <input type="text" name="longitud" id="longitud">
    </p>

    <p>
        <label for="fechaSalida">Fecha de salida</label>
        <input type="text" name="fechaSalida" id="fechaSalida" placeholder="YYYY-MM-DD">
    </p>

    <p>
        <label for="horaSalida">Hora de salida</label>
        <input type="text" name="horaSalida" id="v" placeholder="HH:MM">
    </p>

    <p>
        <label for="fechaRegreso">Fecha de regreso</label>
        <input type="text" name="fechaRegreso" id="fechaRegreso" placeholder="YYYY-MM-DD">
    </p>

    <p>
        <label for="horaRegreso">Hora de regreso</label>
        <input type="text" name="horaRegreso" id="horaRegreso" placeholder="HH:MM" >
    </p>
    <p>El costo aproximado para este viaje es de: <%=costoTotal(Integer.parseInt(cantidadPasajerosParametro), Double.parseDouble(distanciaParametro))%></p>

    <button type="submit">Enviar formulario</button>
</form>
<%
    } else if (numeroPaso.equalsIgnoreCase("3")) {
%>
<p>Solicitud enviada con exito. Para revisar el estado de su solicitud lo puede hacer en el registro de actividad en el apartado de viajes privados.</p>
<p> !Gracias por preferirnos¡</p>

<%
    }
%>

<form action="${pageContext.request.contextPath}/javascript/VentanaCliente/ventanaInicioCliente.jsp" method="GET">
    <p>
        <button type="submit">Regresar al inicio</button>
    </p>
</form>

<p>${mensaje}</p>
</body>
</html>

<%!
    double costoTotal(int cantidadPasajeros, double distancia){
        double costo = 0.0;
        costo = cantidadPasajeros * distancia;
        return costo;
    }
%>
