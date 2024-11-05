<%-- 
    Document   : index
    Created on : 7 oct 2024, 14:22:12
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario - Calculadora</title>
        
    </head>
    <body>
        
        <!-- Intorducir dos números. dos casillas. y un botón enviar
        elegir operación  4 botones.. + - * / , al puslar uno de los bootnes te manda a
        RESULTADO - aparece el resultado y hay un botón que vuelves al principio. -->
        <h1>Formulario - Calculadora</h1>
        <form action="s1" method="POST">
            <input type="number" placeholder="número 1" name="num1">
            <input type="number" placeholder="número 2" name="num2">
            
            <button type="submit">Enviar</button>
            
        </form>
    </body>
</html>
