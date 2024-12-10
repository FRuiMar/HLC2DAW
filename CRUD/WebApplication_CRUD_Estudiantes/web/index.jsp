<%@ page import="clases.ConnMysql" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Alumnos</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin: 15px auto;
        }
        th, td {
            border: 1px solid black;
            padding: 5px;
            text-align: center;
        }
        h1{
            text-align: center
        }
        
    </style>
</head>
<body>
    <h1>Listado de Alumnos</h1>

    <!-- Tabla de alumnos -->
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Fecha de Nacimiento</th>
            <th>Nota</th>
            <th>Acciones</th>
        </tr>
        <%
            
            // Realizo la conexión a la base de datos
            ConnMysql connMysql = new ConnMysql();
            Connection conn = connMysql.getConnection();

            //creo el statement y lo ejecuto. Lo guardamos en el resultset.
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM estudiante");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {   //del resultset sacamos los datos del estudiante.
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String fechaN = rs.getString("fecha");
                    double nota = rs.getInt("nota");
                    
            //Es como con php.
        %>
        <!-- Hacemos el formulario que es la fila de la tabla..-->
        <form action="AlumnosServlet" method="post">
            <tr>
                <td><%= id %></td>
                <td>                 <!-- Meto los datos obtenidos en los inputs value.-->
                    <input type="text" name="nombre" value="<%= nombre %>" required> 
                </td>
                <td>
                    <input type="date" name="fecha_n" value="<%= fechaN %>" required>
                </td>
                <td>
                    <input type="number" name="nota" value="<%= nota %>" required>
                </td>
                <td>                        <!-- Arrastro el valor id con hidden..-->
                    <input type="hidden" name="id" value="<%= id %>">
                    <button type="submit" name="action" value="update">Modificar</button>
                    <button type="submit" name="action" value="delete">Borrar</button>
                </td>
            </tr>
        </form>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connMysql.cerrarConexion();
            }
        %>

        <!-- Creo una fila al final para insertar alumnos nuevos -->
        <form action="AlumnosServlet" method="post">
            <tr>
                <td></td> <!-- ID vacío -->
                <td><input type="text" name="nombre" placeholder="Nombre" required></td>
                <td><input type="date" name="fecha" required></td>
                <td><input type="number" name="nota" placeholder="Nota" required></td>
                <td>
                    <input type="hidden" name="action" value="insert">
                    <button type="submit">Insertar</button>
                </td>
            </tr>
        </form>
    </table>
</body>
</html>
