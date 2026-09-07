<%@ page import="Objetos.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 2/9/26
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<html>
<head>
    <title>Actualizar datos</title>
</head>
<body>

<h1>Actualización de datos</h1>
<h2>datos actuales: </h2>

<form ACTION="${pageContext.request.contextPath}/ClienteServlet" METHOD="POST">
    <input type="hidden" name="evento" value="actualizarDatos">
    <p> <label for="telefono">Teléfono:</label> <input type="text" name="telefono" value="<%= usuario.getTelefono() %>"></p>
    <p> <label for="direccion">Dirección:</label> <input type="text" name="direccion" value="<%= usuario.getDireccion() %>"></p>
    <p> <label for="correo">Correo:</label> <input type="text" name="correo" value="<%= usuario.getCorreo() %>"></p>
    <br>
    <p> <label for="usuario">Usuario:</label> <input type="text" name="usuario" value="<%= usuario.getUsuario() %>"></p>
    <p> <label for="clave">Contraseña:</label> <input type="password" name="clave" placeholder="Contraseña"></p>
    <p> <label for="confirmarClave">Confirmar contraseña:</label> <input type="password" name="confirmarClave" placeholder="confirmar Contraseña"></p>
    <button type="submit">Actualizar Datos</button>
</form>

<button type="button" onclick="regresarVentanaInicioCliente()">Regresar al inicio</button>

<p>${mensaje}</p>
<script src="/Proyecto_1_IPC2/javascript/VentanaCliente/navegarVentanaCliente.js"></script>

</body>
</html>
