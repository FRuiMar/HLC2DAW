<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Matriz Mágica</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Crear Matriz Mágica</h1>
    <form action="Servlet" method="post"> <!-- Asegúrate de que el action apunte a tu servlet -->
        Ingresa un número impar: <input type="number" name="numero" required>   <!-- //name= "numero"   este es el id al que luego referiremos para adquirir la info. -->
        <input type="submit" value="Crear Matriz">  <!-- revisar -->
    </form>
</body>
</html>