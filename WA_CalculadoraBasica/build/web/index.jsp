<%-- 
    Document   : index
    Created on : Sep 28, 2024, 6:21:27 PM
    Author     : FMHJ97
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WA - Calculadora Básica</title>
        <style>
            p {
                font-size: 1.5em;
            }
        </style>
    </head>
    <body>
        <h1>Calculadora Básica (Int)</h1>
        <form action="s1" method="post">
            <p>Número 1: <input type="number" name="num1"></p>
            <p>Número 2: <input type="number" name="num2"></p>
            <button type="submit" name="button" value="1">Sumar</button>
            <button type="submit" name="button" value="2">Restar</button>
            <button type="submit" name="button" value="3">Multiplicar</button>
            <button type="submit" name="button" value="4">Dividir</button>
        </form>
    </body>
</html>
