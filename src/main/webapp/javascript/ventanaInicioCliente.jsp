<%@ page import="Objetos.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: josue
  Date: 29/8/26
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio</title>
</head>
<body>
<% Usuario usuario = (Usuario) session.getAttribute("usuario");%>
<H1>USUARIO: <%=usuario.getNombre()%></H1>
</body>
</html>
