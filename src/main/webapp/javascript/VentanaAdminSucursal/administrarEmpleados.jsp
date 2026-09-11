<%@ page import="Objetos.Usuario" %>
<%@ page import="BaseDatos.ChoferDB" %>
<%@ page import="Objetos.Chofer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 7/9/26
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String idChoferActualizar = null;

    if (request.getAttribute("idChoferActualizar") != null) {
        idChoferActualizar = request.getAttribute("idChoferActualizar").toString();
    } else {
        idChoferActualizar = request.getParameter("idChoferActualizar");
    }

    String nuevoChofer = null;

    if (request.getAttribute("nuevoChofer") != null) {
        nuevoChofer = request.getAttribute("nuevoChofer").toString();
    } else {
        nuevoChofer = request.getParameter("nuevoChofer");
    }

    Usuario usuario = (Usuario) session.getAttribute("usuario");
    ChoferDB choferDB = new ChoferDB();
    List<Chofer> choferes = null;
%>

<html>
<head>
    <title>Title</title>
</head>
<body>


<h2>Choferes de la sucursal</h2>

<table border="1">
    <tr>
        <th>NO. chofer</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>NO.licencia</th>
        <th>Tipo licencia</th>
        <th>Vencimiento licencia</th>
        <th>Teléfono</th>
        <th>Salario</th>
        <th>Estado de actividad</th>
        <th>estado</th>
        <th>Imagen</th>
        <th></th>
    </tr>

    <%
        try {
            choferes = choferDB.choferesSucursal(usuario.getId_sucursalAdministra());
            if (choferes != null && !choferes.isEmpty()){
                for (Chofer chofer: choferes){
    %>
    <tr>
        <td><%= chofer.getId_chofer() %></td>
        <td><%= chofer.getNombre() %></td>
        <td><%= chofer.getApellido() %></td>
        <td><%= chofer.getLicencia() %></td>
        <td>TIPO <%= chofer.getTipoLicencia().toUpperCase() %></td>
        <td><%= chofer.getVencimientoLicencia() %></td>
        <td><%= chofer.getTelefono() %></td>
        <td><%= chofer.getSalarioBaseViaje() %></td>
        <td><%= chofer.getEstadoActividad() %></td>
        <td><%= chofer.getEstado() %></td>
        <td>
            <img src="<%= request.getContextPath() %>/ver-imagen?nombre=<%= chofer.getFoto() %>" alt="chofer" width="100">
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/SucursalChoferServlet" method="post">
                <input type="hidden" name="evento" value="cambiarEstadoChofer">
                <input type="hidden" name="estadoActividad" value="<%= chofer.getEstadoActividad() %>">
                <input type="hidden" name="idChofer" value="<%= chofer.getId_chofer() %>">
                <input type="hidden" name="estado" value="<%= chofer.getEstado() %>">
                <button type="submit">Habilitar/Deshabilitar</button>
            </form>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/SucursalChoferServlet" method="post">
                <input type="hidden" name="evento" value="eliminarChofer">
                <input type="hidden" name="estadoActividad" value="<%= chofer.getEstadoActividad() %>">
                <input type="hidden" name="idChofer" value="<%= chofer.getId_chofer() %>">
                <button type="submit">Eliminar</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
        if (choferes == null && choferes.isEmpty()){
    %>
    <p>Esta sucursal no cuenta con choferes en el sistema.</p>
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

<form action="<%= request.getContextPath() %>/javascript/VentanaAdminSucursal/administrarEmpleados.jsp" method="get">
    <label for="idChoferActualizar">Chofer NO.: </label>
    <select id="idChoferActualizar" name="idChoferActualizar">
        <%
            if (choferes != null && !choferes.isEmpty()){
                for(Chofer chofer: choferes){
                    String selected = (idChoferActualizar != null && idChoferActualizar.equals(String.valueOf(chofer.getId_chofer()))) ? "selected" : "";
        %>
        <option value="<%= chofer.getId_chofer() %>" <%= selected %>><%= chofer.getId_chofer()%></option>
        <%
            }
        } else {
        %>
        <option value="">No se han ingresado Choferes en la sucursal</option>
        <%
            }
        %>
    </select>

    <button type="submit">Cargar Chofer</button>

</form>


<%
    if (idChoferActualizar != null && !idChoferActualizar.isEmpty()) {
        Chofer chofer = choferDB.choferSucursal(Integer.parseInt(idChoferActualizar)).get();
%>
<form action="${pageContext.request.contextPath}/SucursalChoferServlet" method="post">
    <input type="hidden" name="evento" value="actualizarDatos">
    <input type="hidden" name="estadoActividad" value="<%= chofer.getEstadoActividad() %>">
    <input type="hidden" name="estado" value="<%= chofer.getEstado() %>">

    <p>
        <label for="idChofer">Número de Chofer:</label>
        <input type="text" id="idChofer" name="idChofer" value="<%= idChoferActualizar %>" readonly>
    </p>

    <p>
        <label for="vencimientoLicencia">Vencimiento de Licencia:</label>
        <input type="text" id="vencimientoLicencia" name="vencimientoLicencia">
    </p>

    <p>
        <label for="telefono">teléfono:</label>
        <input type="text" id="telefono" name="telefono">
    </p>

    <p>
        <label for="salarioViaje">Salario base por viaje:</label>
        <input type="text" id="salarioViaje" name="salarioViaje">
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


<h2>Ingresar nuevo Chofer: </h2>

<form action="<%= request.getContextPath() %>/javascript/VentanaAdminSucursal/administrarEmpleados.jsp" method="get">
    <input type="hidden" name="nuevoChofer" value="true">
    <button>Nuevo chofer</button>
</form>


<%
    if (nuevoChofer != null && !nuevoChofer.isEmpty()){
%>
<h3>Datos del nuevo Bus: </h3>

<form action="${pageContext.request.contextPath}/SucursalChoferServlet" method="POST">
    <input type="hidden" name="evento" value="ingresarNuevoChofer">
    <input type="hidden" name="idSucursal" value="<%= usuario.getId_sucursalAdministra()%>">
    <p>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre">
    </p>

    <p>
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" >
    </p>

    <p>
        <label for="licencia">Licencia:</label>
        <input type="text" id="licencia" name="licencia">
    </p>

    <p>
        <label for="tipoLicencia">Tipo de licencia:</label>
        <input type="text" id="tipoLicencia" name="tipoLicencia" placeholder="A,B,C">
    </p>

    <p>
        <label for="vencimientoLicencia">Vencimiento de licencia:</label>
        <input type="text" id="vencimientoLicencia" name="vencimientoLicencia" placeholder="YYYY-MM-DD">
    </p>

    <p>
        <label for="telefono">Teléfono:</label>
        <input type="text" id="telefono" name="telefono">
    </p>

    <p>
        <label for="salarioBaseViaje">Salario base por viaje:</label>
        <input type="text" id="salarioBaseViaje" name="salarioBaseViaje">
    </p>

    <p>
        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen" placeholder="image.png">
    </p>


    <button type="submit">Ingresar Chofer</button>

</form>
<%
    }
%>

<p>${mensajeInformativoNuevochofer}</p>


<form action="${pageContext.request.contextPath}/javascript/VentanaAdminSucursal/ventanaInicioAdminSucursal.jsp" method="GET">
    <p>
        <button type="submit">Regresar al inicio</button>
    </p>
</form>

</body>
</html>
