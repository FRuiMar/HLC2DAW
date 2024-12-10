<%@ page import="clases.ConnMysql" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ADMINISTRACIÓN PARTIDOS</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

        <style>
            table {
                border-collapse: collapse;
                width: 90%;
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
            .action-buttons {
                display: flex; /* Alinea los botones en fila */
                gap: 10px; /* Espaciado entre botones */
                justify-content: center; /* Centra los botones en la celda */
            }
        </style>
    </head>
    <body>
        <h1 style="text-align:center;">GESTIÓN DE LOS PARTIDOS</h1>

        <!-- Tabla de partidos -->
        <table>
            <tr>
                <th>ID</th>
                <th></th>
                <th>Equipo Local</th>
                <th>Goles Local</th>
                <th>Goles Visitante</th>
                <th>Equipo Visitante</th>
                <th></th>
                <th>Acciones</th>
            </tr>
            <%
                // Conexión a la base de datos
                ConnMysql connMysql = new ConnMysql();
                Connection conn = connMysql.getConnection();

                //Saco toda la INFO de la tabla PARTIDO.
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM partido"); ResultSet rs = stmt.executeQuery()) {

                    //HACEMOS UN BUCLE WHILE.. MIENTRAS HAYA FILAS, VAMOS A SACAR TODA LA INFO Y VAMOS SACANDO EL NOMBRE DEL EQUIPO QUE CORRESPONDA CON SU ID(e1, e2) EN PARTIDO.  y lo metemos en una fila de la tabla con su form.
                    while (rs.next()) { //mientras haya filas
                        int id = rs.getInt("id"); //sacamos el ID DE LPARTIDO.

                        String nombreEquipo1, nombreEquipo2; //CREO LAS VARIABLES PARA LOS NOMBRES.

                        // Nombre del equipo 1 - e1
                        try (Statement stmt2 = conn.createStatement(); ResultSet rsEquipo1 = stmt2.executeQuery("SELECT nombre FROM equipo WHERE id = " + rs.getInt("e1"))) {
                            nombreEquipo1 = rsEquipo1.next() ? rsEquipo1.getString("nombre") : "Desconocido";
                        }

                        // Nombre del equipo 2 - e2
                        try (Statement stmt3 = conn.createStatement(); ResultSet rsEquipo2 = stmt3.executeQuery("SELECT nombre FROM equipo WHERE id = " + rs.getInt("e2"))) {
                            nombreEquipo2 = rsEquipo2.next() ? rsEquipo2.getString("nombre") : "Desconocido";
                        }
            %>
            <tr>
            <form method="post" action="ClasificacionServlet">
                <td><%= id%></td>
                <td><img src="Imagenes/<%= rs.getString("e1")%>.png" alt="<%= nombreEquipo1%>"></td>
                <td>
                    <select name="equipo1">
                        <%
                            // Obtengo todos los nombres de equipos para poner en el select
                            try (Statement stmtEquipos = conn.createStatement(); ResultSet rsEquipos = stmtEquipos.executeQuery("SELECT * FROM equipo")) {

                                //hacemos otro bucle para ir poniendo todos ids y nombres de los equpos.
                                while (rsEquipos.next()) {
                        %>
                        <!-- genero los options con el value que es el id, y luego el nombre que se ve -->
                        <option value="<%= rsEquipos.getInt("id")%>" <%= rsEquipos.getInt("id") == rs.getInt("e1") ? "selected" : ""%>>   <!-- Saco el id, y verifico que es el mismo que "e1" que sacamos de partido al principio. -->
                            <%= rsEquipos.getString("nombre")%>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </td>
                <td><input type="number" name="goles1" value="<%= rs.getInt("g1")%>" required></td>
                <td><input type="number" name="goles2" value="<%= rs.getInt("g2")%>" required></td>
                <td>
                    <select name="equipo2">
                        <%
                            try (Statement stmtEquipos = conn.createStatement(); ResultSet rsEquipos = stmtEquipos.executeQuery("SELECT * FROM equipo")) {
                                while (rsEquipos.next()) {
                        %>
                        
                        <option value="<%= rsEquipos.getInt("id")%>"<%= rsEquipos.getInt("id") == rs.getInt("e2") ? "selected" : ""%>>
                            <%= rsEquipos.getString("nombre")%>
                        </option>
                        
                        <%
                                }  //cierro el try y while.
                            }
                        %>
                    </select>
                </td>
                <td><img src="Imagenes/<%= rs.getString("e2")%>.png" alt="<%= nombreEquipo2%>"></td>
                <td>
                    <input type="hidden" name="id" value="<%= id%>">
                    <button type="submit" name="action" value="Actualizar" class="btn btn-warning">Actualizar</button>
                    <button type="submit" name="action" value="Borrar" class="btn btn-danger">Borrar</button>
            </form>
            <!-- Creo otro form para borrar  pero en la misma fila. -->
            <form method="post" action="ClasificacionServlet">
                <input type="hidden" name="id" value="<%= id%>">

            </form>
        </td>
    </tr>
    <%
        }
    %>
    <!-- Formulario de insert -->
    <tr>
    <form method="post" action="ClasificacionServlet">
        <td></td>
        <td></td>
        <td>
            <select name="equipo1">
                <%
                    try (Statement stmtEquipos = conn.createStatement(); ResultSet rsEquipos = stmtEquipos.executeQuery("SELECT * FROM equipo")) {
                        while (rsEquipos.next()) {
                %>
                <option value="<%= rsEquipos.getInt("id")%>">
                    <%= rsEquipos.getString("nombre")%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </td>
        <td><input type="text" name="goles1" placeholder="Goles 1"></td>
        <td><input type="text" name="goles2" placeholder="Goles 2"></td>
        <td>
            <select name="equipo2">
                <%
                    try (Statement stmtEquipos = conn.createStatement(); ResultSet rsEquipos = stmtEquipos.executeQuery("SELECT * FROM equipo")) {
                        while (rsEquipos.next()) {
                %>
                <option value="<%= rsEquipos.getInt("id")%>">
                    <%= rsEquipos.getString("nombre")%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </td>
        <td>
            <button type="submit" name="action" value="Insertar" class="btn btn-success">Insertar</button>
        </td>
        <td class="text-end">
            <button type="submit" name="action" value="Cerrar" class="btn btn-dark">Cerrar</button>
        </td>
    </form>
</tr>
<%
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        connMysql.cerrarConexion();
    }
%>
</table>
</body>
</html>
