<%@ page import="Objetos.Usuario" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="BaseDatos.BusDB" %>
<%@ page import="Objetos.Bus" %><%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 7/9/26
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String idBusActualizar = null;

    if (request.getAttribute("idBusActualizar") != null) {
        idBusActualizar = request.getAttribute("idBusActualizar").toString();
    } else {
        idBusActualizar = request.getParameter("idBusActualizar");
    }

    String nuevoBus = null;

    if (request.getAttribute("nuevoBus") != null) {
        nuevoBus = request.getAttribute("nuevoBus").toString();
    } else {
        nuevoBus = request.getParameter("nuevoBus");
    }

    Usuario usuario = (Usuario) session.getAttribute("usuario");
    BusDB busDB = new BusDB();
    List<Bus> buses = null;
%>

<html>
<head>
    <title>Title</title>
</head>
<body>


<h2>Buses de la sucursal</h2>

<table border="1">
    <tr>
        <th>NO. Bus</th>
        <th>Numero de placa</th>
        <th>Marca</th>
        <th>Modelo</th>
        <th>Año de fabricación</th>
        <th>Kilometraje actual</th>
        <th>Capacidad de pasajeros</th>
        <th>Estado de actividad</th>
        <th>estado</th>
        <th>Imagen</th>
        <th></th>
    </tr>

    <%
        try {
            buses = busDB.busesSucursal(usuario.getId_sucursalAdministra());
            if (buses != null && !buses.isEmpty()){
                for (Bus bus: buses){
    %>
    <tr>
        <td><%= bus.getId_bus() %></td>
        <td><%= bus.getNumeroPlaca() %></td>
        <td><%= bus.getMarca() %></td>
        <td><%= bus.getModelo() %></td>
        <td><%= bus.getAñoFabricacion() %></td>
        <td><%= bus.getKilometrajeActual() %></td>
        <td><%= bus.getCapacidadPasajeros() %></td>
        <td><%= bus.getEstadoActividad() %></td>
        <td><%= bus.getEstado() %></td>
        <td>
            <img src="<%= request.getContextPath() %>/ver-imagen?nombre=<%= bus.getImagen() %>" alt="Bus" width="100">
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/SucursalBusServlet" method="post">
                <input type="hidden" name="evento" value="cambiarEstadoBus">
                <input type="hidden" name="estadoActividad" value="<%= bus.getEstadoActividad() %>">
                <input type="hidden" name="idBus" value="<%= bus.getId_bus() %>">
                <input type="hidden" name="estado" value="<%= bus.getEstado() %>">
                <button type="submit">Habilitar/Deshabilitar</button>
            </form>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/SucursalBusServlet" method="post">
                <input type="hidden" name="evento" value="eliminarBus">
                <input type="hidden" name="estadoActividad" value="<%= bus.getEstadoActividad() %>">
                <input type="hidden" name="idBus" value="<%= bus.getId_bus() %>">
                <button type="submit">Eliminar</button>
            </form>
        </td>
    </tr>
    <%
                }
            }
        if (buses == null && buses.isEmpty()){
    %>
<p>Esta sucursal no cuenta con Buses en el sistema.</p>
<%
        }
    } catch (SQLException e) {
        e.printStackTrace();
%>
    <p>Error al cargar los datos, intente nuevamente.</p>
    <%
        }
    %>
</table>
<p>${mensajeInformativoEstado}</p>

<h2>Actualizar datos</h2>

<form action="<%= request.getContextPath() %>/javascript/VentanaAdminSucursal/administrarBuses.jsp" method="get">
    <label for="idBusActualizar">Bus NO.: </label>
    <select id="idBusActualizar" name="idBusActualizar">
        <%
            if (buses != null && !buses.isEmpty()){
                for(Bus bus: buses){
                    String selected = (idBusActualizar != null && idBusActualizar.equals(String.valueOf(bus.getId_bus()))) ? "selected" : "";
        %>
        <option value="<%= bus.getId_bus()%>" <%= selected %>><%= bus.getId_bus()%></option>
        <%
            }
        } else {
        %>
        <option value="">No se han ingresado buses en la sucursal</option>
        <%
            }
        %>
    </select>
    <button type="submit">Cargar Bus</button>

</form>


<%
    if (idBusActualizar != null && !idBusActualizar.isEmpty()) {
%>
<form action="${pageContext.request.contextPath}/SucursalBusServlet" method="post">
    <input type="hidden" name="evento" value="actualizarDatos">

    <p>
        <label for="idBus">Número de bus:</label>
        <input type="text" id="idBus" name="idBus" value="<%= idBusActualizar %>" readonly>
    </p>

    <p>
        <label for="capacidadPasajeros">Capacidad de pasajeros:</label>
        <input type="text" id="capacidadPasajeros" name="capacidadPasajeros">
    </p>

    <p>
        <label for="kilometraje">Kilometraje Actual:</label>
        <input type="text" id="kilometraje" name="kilometraje">
    </p>

    <button type="submit">Guardar Cambios</button>
</form>

<form>
    <button>cerrar</button>
</form>
<%
    }
%>

<p>${mensajeInformativoActualizacion}</p>

<h2>Ingresar nuevo Bus: </h2>

<form action="<%= request.getContextPath() %>/javascript/VentanaAdminSucursal/administrarBuses.jsp" method="get">
    <input type="hidden" name="nuevoBus" value="true">
    <button>Nuevo bus</button>
</form>

<%
    if (nuevoBus != null && !nuevoBus.isEmpty()){
%>
<h3>Datos del nuevo Bus: </h3>
<form action="${pageContext.request.contextPath}/SucursalBusServlet" method="POST">
    <input type="hidden" name="evento" value="ingresarNuevoBus">
    <input type="hidden" name="idSucursal" value="<%= usuario.getId_sucursalAdministra()%>">
    <p>
        <label for="numeroPlaca">Número de placa:</label>
        <input type="text" id="numeroPlaca" name="numeroPlaca" placeholder="cx-545">
    </p>

    <p>
        <label for="año">Año de fabricación:</label>
        <input type="text" id="año" name="año" placeholder="YYYY">
    </p>

    <p>
        <label for="marca">Marca:</label>
        <input type="text" id="marca" name="marca">
    </p>

    <p>
        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" name="modelo">
    </p>

    <p>
        <label for="capacidad">Capacidad de pasajeros:</label>
        <input type="text" id="capacidad" name="capacidad" placeholder="xx">
    </p>

    <p>
        <label for="kilometraje">Kilometraje Actual:</label>
        <input type="text" id="kilometraje" name="kilometraje" placeholder="xxxx">
    </p>

    <p>
        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen" placeholder="image.png">
    </p>

    <button type="submit">Ingresar Bus</button>

</form>
<%
    }
%>

<p>${mensajeInformativoNuevoBus}</p>


<form action="${pageContext.request.contextPath}/javascript/VentanaAdminSucursal/ventanaInicioAdminSucursal.jsp" method="GET">
    <p>
        <button type="submit">Regresar al inicio</button>
    </p>
</form>

</body>
</html>
