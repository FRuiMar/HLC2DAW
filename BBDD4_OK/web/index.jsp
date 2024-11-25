<%@page import="p1.Ejecuta"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            table, tr, td, th {
                border: 1px solid black;
                border-collapse: collapse;
            }
            input {
                border: none;
            }
            .button {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>

        <%
            ResultSet rs = new p1.Ejecuta("select * from persona").getResult();

            out.println("<table>");
            out.println("<tr><th>ID</th> <th>NOMBRE</th><th>NOTA</th><th>FECHA_NACIMIENTO</th><th>ACCIONES</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<form action='s1' method='post'>");
                out.println("<td>" + rs.getInt(1) + "</td>");
                out.println("<td><input type='text' name='nombre' value='" + rs.getString(2) + "'></td>");
                out.println("<td><input type='text' name='nota' value='"  + rs.getString(3) + "'></td>");
                out.println("<td><input type='text' name='fecha' value='"  + rs.getDate(4) +  "'></td>");
                out.println("<td>");
                out.println("<input  type='hidden' name='id' value='" + rs.getInt(1) + "'>");
                out.println("<input class='button' type='submit' name='borrar' value='borrar'>");
                out.println("<input class='button' type='submit' name='editar' value='editar'>");
                out.println("</td>");
                out.println("</form>");
                out.println("</tr>");

            }
            out.println("<tr><form action='s1' method='post'><td></td> <th><input type='text' name='nombre'></td><td><input type='number' name='nota'></td><td><input type='text' name='fecha'></td><td><input type='submit' class='button' name='insert' value='insertar'></form></td></tr>");
            out.println("</table>");

            //   rs.absolute(1);
            // rs.deleteRow();
            //  rs.updateString("nombre", "juana");
            //   rs.updateRow();
            //  rs.moveToInsertRow();
            // rs.updateInt(1, 3);
            // rs.updateString("nombre", "JORGE");
            // rs.updateInt(3, 5);
            // rs.insertRow();

        %>
    </body>
</html>
