<%-- 
    Document   : index
    Created on : 7 oct 2024, 13:38:22
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
        <form action="s1" method="POST">
            <p>Selecciona tus intereses: </p>
            
            <p><input type="checkbox" name="intereses[]" value="peliculas" />Peliculas</p>
            <p><input type="checkbox" name="intereses[]"  value="libros"/>Libros</p>
            <p><input type="checkbox" name="intereses[]"  value="internet" />Internet</p>
            
            <br>
            
            <p>Selecciona un país: </p>
            <select name="select1">
                <option value="Es">España</option>
                <option value="Fr">Francia</option>
                <option value="Pt">Portugal</option><br>
            </select>
            
            
            <br>
            
            <p>Selecciona un color: </p>
            <p><input type="radio" name="rd1" value="rojo" checked/>Rojo</p>
            <p><input type="radio" name="rd1"  value="verde"/>Verde</p>
            <p><input type="radio" name="rd1"  value="azul" />Azul</p>
            
            <br>
            <p> <button type="submit"> Enviar </button></p>

        </form>
    </body>
</html>
