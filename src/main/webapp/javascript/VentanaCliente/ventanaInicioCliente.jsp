<%@ page import="Objetos.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 29/8/26
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String vista = request.getParameter("vista");
    if (vista == null || vista.isEmpty()) {
        vista = "general";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Panel de Cliente</title>
</head>
<body>

<h1>Buses Extraurbanos Xela</h1>
<hr>

<div>
    <h2>Gestion de boletos y alquiler de buses</h2>
    <p>Usuario: <%=usuario.getUsuario()%></p>
    <p>Saldo: <%=usuario.getSaldoCartera()%></p>

    <br>
    <h3>Menú de Opciones</h3>

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
            <button type="submit">6. Recargar saldo</button>
        </p>
    </form>

    <form action="${pageContext.request.contextPath}/javascript/VentanaCliente/actualizarDatos.jsp" method="GET">
        <p>
            <input type="hidden" name="vista" value="perfil">
            <button type="submit">5. Actualizar datos</button>
        </p>
    </form>
</div>

</body>
</html>