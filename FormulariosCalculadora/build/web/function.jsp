<%-- 
    Document   : function
    Created on : 7 oct 2024, 14:36:02
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Function</title>
    </head>
    <body>
        <h1>Eligue la Operación a realizar: </h1>
        <form action="s1" method="POST">
            
            <button type="submit" name="operacion" value="1">Sumar</button>
            <button type="submit" name="operacion" value="2">Restar</button>
            <button type="submit" name="operacion" value="3">Multiplicar</button>
            <button type="submit" name="operacion" value="4">Dividir</button>
            
        </form>
        
    </body>
</html>
