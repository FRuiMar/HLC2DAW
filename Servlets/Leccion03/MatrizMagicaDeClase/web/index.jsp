<%-- 
    Document   : index
    Created on : 30 sept 2024, 14:01:01
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
        <h1>Matriz Mágica</h1>
        <form action="Servlet" method="post">
            <p>Introduzca un número impar<input type="number" name="num"></p>
            <button type="submit" name="generar">Generar Matriz</button>  
        </form>
        <% request.setAttribute("cadena", "micadena");
        
        %>
        
    </body>
</html>
