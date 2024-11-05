<%-- 
    Document   : saldo
    Created on : 28 oct 2024, 13:56:28
    Author     : diurno
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Saldo de Pepe</h1>
        
        <% 
       
        
        out.println("<form action='s2'  method=\"POST\">");
        Usuario user = (Usuario) session.getAttribute("user");
        out.println(user.getUsuario());
        out.println("Saldo: " + user.getSaldo());
        
        
        
        
        %>
    </body>
</html>
