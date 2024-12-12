<%-- 
    Document   : index
    Created on : 11 dic 2024, 11:26:25
    Author     : diurno
--%>


<%@page import="clases.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


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
            <h1>Inicio de Sesion</h1>


            <form action="s1exam" method="post">

                <p>Usuario: <input type="text" id="user" name="user"></p>
                <p>Contraseña: <input type="password" id="pwd" name="pwd"></p>

                <button type="submit">Iniciar sesión</button>
            </form>

        </div>
    </body>
</html>
