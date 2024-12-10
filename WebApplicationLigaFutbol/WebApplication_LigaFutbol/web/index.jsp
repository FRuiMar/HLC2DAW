<%@ page import="clases.ConnMysql" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clasificación fútbol</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
 
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            padding: 8px;
            text-align: center;
        }
        td {
            border-top: 1px solid black; /* Solo líneas horizontales */
        }
        img{
            width: 40px;
            height: 40px;
        }
        form {
            display: flex; /* Coloca elementos en fila */
            align-items: center; /* Centra verticalmente */
            margin-left: 10%; /* Alinea a la izquierda debajo de la tabla */
            margin-top: 20px; /* Espaciado con la tabla */
        }
        form input[type="text"] {
            margin-right: 10px; /* Espaciado entre el input y el botón */
            width: auto; /* Ajusta el ancho del input */
        }
    </style>
</head>
<body>
    <h1 style="text-align:center;">CLASIFICACIÓN ACTUAL DE LA LIGA</h1>

    <!-- Tabla de alumnos -->
    <table>
        <tr>
            <th>ID</th>
            <th>Escudo</th>
            <th>Nombre</th>
            <th>Puntos</th>
            <th>Victorias</th>
            <th>Empates</th>
            <th>Derrotas</th>
            <th>Goles Favor</th>
            <th>Goles Contra</th>
        </tr>
        <%
            // CONEXIÓN A LA BBDD.
            ConnMysql connMysql = new ConnMysql();
            Connection conn = connMysql.getConnection();
            
            //Preparo el statement.
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM equipo ORDER BY puntos DESC");
                 ResultSet rs = stmt.executeQuery()) { //ejecuto el query.
                while (rs.next()) {  //saco los datos del resultSet.
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String puntos = rs.getString("puntos");
                    String victorias = rs.getString("v");
                    String empates = rs.getString("e");
                    String derrotas = rs.getString("d");
                    String goles_favor = rs.getString("gf");
                    String goles_contra = rs.getString("gc");
                    
        %>
        <tr>
            <td><%= id %></td>
            <td><img src="Imagenes/<%= id %>.png" alt="<%= nombre %>"></td>
            <td><%= nombre %></td>
            <td><%= puntos %></td>
            <td><%= victorias %></td>
            <td><%= empates %></td>
            <td><%= derrotas %></td>
            <td><%= goles_favor %></td>
            <td><%= goles_contra %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connMysql.cerrarConexion();
            }
        %>
    </table>
    
   
    <!-- Administrador-->
    <form method="post" action="ClasificacionServlet">
        <input type="text" name="clave" placeholder="Contraseña" class="form-control mb-2" required>
        <input type="hidden" name="action" value="logear">
        <button type="submit" class="btn btn-primary">Administrador</button>
    </form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
