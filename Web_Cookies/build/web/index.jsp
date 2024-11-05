<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WA - Lista de Usuarios</title>
        <style>
            p, button {
                font-size: 1.5em;
            }
            input {
                font-size: 1em;
            }
        </style>
    </head>
    <body>
        <h1>Comprobación de credenciales</h1>

        <!-- Vamos a meter todo dentro -->
        <%

            Usuario u = null;  //lo declaro y lo dejo a nulo.
            u = (Usuario) session.getAttribute("user");        //para intentar recuperarlo de la variable de usuario(que haya iniciado sesión antes) S
            //si no hay, devuelve null, y verifico abajo

            if (session.isNew() || u == null) {     //si la sesión es nueva O si el usuario no está creado DA NULL, entonces.. 
                            
                String mensaje = (String)session.getAttribute("msg");
                if(mensaje != null){
                    out.println("<span style='color:red'>" + mensaje + "</span>");
                }
                

                out.println("<form action=\"s1\" method=\"post\">");
                out.println("<p>Usuario: <input type='text' name='user'></p>");
                out.println("<p>Contraseña: <input type='text' name='pwd'></p>");
                out.println("<button type='submit' name='button'>Iniciar Sesión</button>");
                out.println("</form>");
            } else {
                out.println("Usuario autenticado: " + u.getUsuario());
            }

            //String mensaje = (String) request.getAttribute("mensaje"); 
            //if (mensaje != null) {
            //        out.println(mensaje + "<br>");
            //}
        %>  



    </body>
</html>
