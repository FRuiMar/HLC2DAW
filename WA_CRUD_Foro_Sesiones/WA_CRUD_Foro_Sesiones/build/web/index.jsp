<%-- 
    Document   : index
    Created on : 29 nov 2024, 12:42:00
    Author     : diurno
--%>

<%@page import="clases.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("login") != null) {
        request.getRequestDispatcher("foro.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foro</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container mt-5">
            <h1>Bienvenido a ForoMotos!</h1>


            <form action="s1" method="post">

                <p>Usuario: <input type="text" id="user" name="user"></p>
                <p>Contraseña: <input type="password" id="pwd" name="pwd"></p>

                <button type="submit">Iniciar sesión</button>
            </form>

        </div>
    </body>
</html>
