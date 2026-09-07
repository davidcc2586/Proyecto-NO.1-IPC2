<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear cuenta</title>
</head>
<body>

<h1>¡Bienvenido a "Buses Extraurbanos Xela"!</h1>
<h2>Crear cuenta</h2>

<form action="/Proyecto_1_IPC2/CrearCuentaServlet" method="POST">
    <p> <label for="">Nombres:</label> <input type="text" name="nombre" placeholder="nombres"></p>
    <p> <label for="apellido">Apellidos:</label> <input type="text" name="apellido" placeholder="apellidos"></p>
    <p> <label for="dpi">DPI:</label> <input type="text" name="dpi" placeholder="xxxxxxxxxxxxx"></p>
    <p> <label for="telefono">Teléfono:</label> <input type="text" name="telefono" placeholder="(XXXX XXXX)"></p>
    <p> <label for="direccion">Dirección:</label> <input type="text" name="direccion" placeholder="dirección"></p>
    <p> <label for="correo">Correo:</label> <input type="text" name="correo" placeholder="correo"></p>
    <p> <label for="nit">Nit:</label> <input type="text" name="nit" placeholder="nit"></p>
    <br>
    <p> <label for="usuario">Usuario:</label> <input type="text" name="usuario" placeholder="Usuario"></p>
    <p> <label for="clave">Contraseña:</label> <input type="password" name="clave" placeholder="Contraseña"></p>
    <p> <label for="confirmarClave">Confirmar contraseña:</label> <input type="password" name="confirmarClave" placeholder="confirmar Contraseña"></p>
    <button type="submit">Crear Cuenta</button>
</form>

<p>${mensaje}</p>

</body>
</html>