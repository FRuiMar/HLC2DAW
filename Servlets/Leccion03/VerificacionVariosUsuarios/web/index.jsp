<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Verificación de Usuario</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1>Login de Usuario</h1>
        <form action="Servlet" method="post"> <!-- Apunta al servlet para procesar el formulario -->
            Usuario: <input type="text" name="nombre" required><br>
            Contraseña: <input type="password" name="password" required><br>
            <input type="submit" value="Iniciar sesión">
        </form>
        
        <!-- Aquí podrías incluir lógica en Java si quisieras -->
        <%-- Ejemplo de incluir un mensaje dinámico en JSP (este es un comentario JSP) --%>
        <% 
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
                out.println("<p>" + mensaje + "</p>");
            }
        %>
    </body>
</html>