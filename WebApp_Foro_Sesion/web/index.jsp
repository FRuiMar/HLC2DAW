<%-- 
    Document   : index
    Created on : 7 dic 2024, 17:36:12
    Author     : Fran Ruiz
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
    <body class="bg-light">
        <div class="container mt-5">
            <div class="text-center mb-4">
                <h1 class="display-5">Bienvenido</h1>
            </div>
            <div class="d-flex justify-content-center">
                <form action="s1" method="post" class="p-4 border rounded bg-white shadow-sm">
                    <div class="mb-3">
                        <label for="user" class="form-label">Usuario:</label>
                        <input type="text" id="user" name="user" class="form-control" placeholder="Tu nombre de usuario">
                    </div>
                    <div class="mb-3">
                        <label for="pwd" class="form-label">Contraseña:</label>
                        <input type="password" id="pwd" name="pwd" class="form-control" placeholder="Tu contraseña">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary w-100">Iniciar sesión</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
