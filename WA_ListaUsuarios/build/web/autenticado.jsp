<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Datos de Usuario Conectado</h1>
        
        <%
            Usuario usuario = (Usuario) request.getAttribute("user");
            out.println("<p>Usuario: " + usuario.getUsuario() + "</p>"
                    + "<p>Password: " + usuario.getPassword() + "</p>");
        %>
    </body>
</html>
