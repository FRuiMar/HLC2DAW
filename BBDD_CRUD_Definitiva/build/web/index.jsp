<%-- 
    Document   : index
    Created on : 30 nov 2024, 13:17:15
    Author     : Fran Ruiz
--%>


<%--  1º Importamos las cosas   con el * todo java.. y la conexión de model/ConnMysql  --%>

<%@page import="model.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            table, tr, td, th {
                border: 2px solid black;
                border-collapse: collapse;
            }
            th, td {
                text-align: center;
                padding: 1em;
                font-size: 1.2rem;
            }
            th {
                background-color: lightblue;
            }
            button, input {
                font-size: 1.2rem;
            }
        </style>
    </head>
    <body>
        <h1>Datos de la BD</h1>
        <form action="s1" method="POST">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Nota</th>
                    <th>Fecha Nacimiento</th>
                    <th>Acción</th>
                </tr>
                <%
                    try {
                        // Creo el objeto Conexión
                        Connection conn = new ConnMysql().getConnection();
                        // Creamos un objeto Statement
                        Statement instruccion = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                        // Creamos el query
                        String sql = "SELECT * FROM estudiante";
                        //Metemos el query en el ResultSet
                        ResultSet rs = instruccion.executeQuery(sql);
                        
                        // Creamos una fila por cada registro.
                        while (rs.next()) {
                            out.println("<tr>");
                            out.println("<td>" + rs.getInt(1) + "</td>");
                            out.println("<td>" + rs.getString(2) + "</td>");
                            out.println("<td>" + rs.getInt(3) + "</td>");
                            out.println("<td>" + rs.getDate(4) + "</td>");
                            out.println("<td>");
                            out.println("<button type='submit' name='borrar' value='" + rs.getInt(1) + "'>Borrar</button>");
                            out.println("</td>");
                            out.println("</tr>");
                        }

                        // Cerrar cada uno de los objetos utilizados
                        rs.close();
                        instruccion.close();
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
                <!--al final de la tabla siempre añado la linea de insertar-->
                <tr>
                    <td></td>  <!-- aquí debería ir el id, pero si lo tengo autoincrement no debería dar problemas -->
                    <td><input type="text" name="nombre"></td>
                    <td><input type="number" name="nota"></td>
                    <td><input type="date" name="fecha_nac"></td>
                    <td><input type="submit" name="insertar" value="Insertar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
