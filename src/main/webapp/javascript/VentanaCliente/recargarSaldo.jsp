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
    <title>Recargar saldo</title>
</head>
<body>
<h2>Recargar el saldo de la cuenta</h2>
<p>Saldo actual: <%= usuario.getSaldoCartera()%></p>
<form ACTION="${pageContext.request.contextPath}/ClienteServlet" METHOD="POST">
    <input type="hidden" name="evento" value="recargarSaldo" >
    <p> <label for="saldoAñadir">Cantidad a recargar:</label> <input type="text" id="saldoAñadir" name="saldoAñadir" placeholder="xxx.x"></p>
    <button type="submit">Agregar</button>
</form>

<button type="button" onclick="regresarVentanaInicioCliente()">Regresar al inicio</button>

<p>${mensaje}</p>
<script src="/Proyecto_1_IPC2/javascript/VentanaCliente/navegarVentanaCliente.js"></script>

</body>
</html>
