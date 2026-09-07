<%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 1/9/26
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buses Extraurbanos Xela</title>
</head>
<body>

<h1>¡Bienvenido a "Buses Extraurbanos Xela"!</h1>

<form ACTION="${pageContext.request.contextPath}/InicioSesionServlet" METHOD="POST">
    <p><label for="usuario">Usuario:</label><input type="text" name ="usuario" placeholder="Usuario"></p>
    <p><label for="contraseña">Contraseña:</label><input type="password" name ="clave" placeholder="Contraseña"></p>
    <button type="submit">Iniciar Sesión</button>
</form>

<button type="button" onclick="crearNuevaCuenta()">Crear Cuenta</button>

<p>${mensaje}</p>
<script src="/Proyecto_1_IPC2/javascript/inicio.js"></script>
</body>
</html>