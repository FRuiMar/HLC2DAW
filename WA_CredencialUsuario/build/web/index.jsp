<%-- 
    Document   : index
    Created on : Sep 28, 2024, 7:04:49 PM
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WA - Credencial Usuario</title>
    </head>
    <style>
        p, button {
            font-size: 1.5em;
        }
        input {
            font-size: 1em;
        }
    </style>
    <body>
        <h1>Comprobación Credenciales</h1>
        <form action="s1" method="post">
            <p>Usuario: <input type="text" name="user"></p>
            <p>Contraseña: <input type="text" name="pwd"></p>
            <button type="submit" name="button">Verificar</button>
        </form>
    </body>
</html>
