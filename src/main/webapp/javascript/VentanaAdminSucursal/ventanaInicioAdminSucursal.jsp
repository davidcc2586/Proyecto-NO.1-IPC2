<%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 7/9/26
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panel de admin Sucursal</title>
</head>
<body>

<h2>Acciones como administrador de sucursal</h2>
<form action="${pageContext.request.contextPath}/javascript/VentanaAdminSucursal/administrarBuses.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="comprar">
        <button type="submit">1. Buses</button>
    </p>
</form>

<form action="${pageContext.request.contextPath}/javascript/VentanaAdminSucursal/administrarEmpleados.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="historial">
        <button type="submit">2. Empleados</button>
    </p>
</form>

<form action="${pageContext.request.contextPath}/javascript/VentanaAdminSucursal/administrarRutas.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="cartera">
        <button type="submit">3. Rutas</button>
    </p>
</form>

<form action="${pageContext.request.contextPath}/javascript/VentanaAdminSucursal/administrarViajes.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="perfil">
        <button type="submit">4. Viajes</button>
    </p>
</form>

<form action="${pageContext.request.contextPath}/javascript/VentanaAdminSucursal/controlViajesDiarios.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="perfil">
        <button type="submit">5. Control de viajes por dia</button>
    </p>
</form>


<form action="${pageContext.request.contextPath}/javascript/VentanaAdminSucursal/registrarGastos.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="perfil">
        <button type="submit">6. Registrar gastos</button>
    </p>
</form>

<form action="${pageContext.request.contextPath}/javascript/VentanaAdminSucursal/reportesSucursal.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="perfil">
        <button type="submit">7. Reportes</button>
    </p>
</form>

<h2>Acciones como usuario</h2>

<form action="${pageContext.request.contextPath}/javascript/VentanaCliente/comprarBoleto.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="comprar">
        <button type="submit">1. Comprar Boletos</button>
    </p>
</form>

<form action="${pageContext.request.contextPath}/javascript/VentanaCliente/alquilarBus.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="historial">
        <button type="submit">2. Alquilar bus</button>
    </p>
</form>

<form action="${pageContext.request.contextPath}/javascript/VentanaCliente/historialViajes.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="cartera">
        <button type="submit">3. Registro de actividad</button>
    </p>
</form>

<form action="${pageContext.request.contextPath}/javascript/VentanaCliente/recargarSaldo.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="perfil">
        <button type="submit">4. Recargar saldo</button>
    </p>
</form>

<form action="${pageContext.request.contextPath}/javascript/VentanaCliente/actualizarDatos.jsp" method="GET">
    <p>
        <input type="hidden" name="vista" value="perfil">
        <button type="submit">5. Actualizar datos</button>
    </p>
</form>

</body>
</html>
