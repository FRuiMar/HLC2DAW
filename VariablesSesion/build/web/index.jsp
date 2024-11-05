<%-- 
    Document   : newjspindex
    Created on : 14 oct 2024, 13:15:27
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
        <h1>Hello World!</h1>
        <!-- No hace falta declarar la clase session. Se crea automaticamente. Cuando lo escribes, te salen los métodos asociados. -->
        <% 
        if (session.isNew()){ 
            out.println("la primera vez que entras");
            session.setAttribute("nombre", "pepe");  //le ponemos el nombre a la sesión PEPE.
        }else{
            out.println("ya has entrado antes");
            String cadena = (String)session.getAttribute("nombre");
            out.println("Nombre: " + cadena);
        }
        %>
    </body>
</html>
