<%-- 
    Document   : index
    Created on : 4 nov 2024, 14:44:09
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Gestión de Cookies</h1>
        <form action="s1" method="POST">
            <p>Nombre para la Cookie: <input type="text" name="nomCookie"></p>
            <p>Valor de la Cookie: <input type="text" name="valor"></p>
            <input type="submit" name="button" value="Crear">
            <input type="submit" name="button" value="Visualizar">
            <input type="submit" name="button" value="Modificar">
            <input type="submit" name="button" value="Eliminar">
        </form>
    </body>
</html>
