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
        </style>
    </head>
    <body>

        <%
            ResultSet rs = new p1.Ejecuta("select * from alumno").getResult();
            out.println("<form action='s1'>");
            out.println("<table>");
            out.println("<tr><th>ID</th> <th>NOMBRE</th><th>NOTA</th><th>FECHA_NACIMIENTO</th><th></th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt(1) + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getString(3) + "</td>");
                out.println("<td>" + rs.getDate(4) + "</td>");
                out.println("<td><input type='submit' name='borrar" + rs.getInt(1) + "' value='Borrar'></td>");
                out.println("</tr>");

            }
            out.println("</form>");
            rs.absolute(1);
            // rs.deleteRow();
            rs.updateString("nombre", "juana");
            rs.updateRow();

            //  rs.moveToInsertRow();
            // rs.updateInt(1, 3);
            // rs.updateString("nombre", "JORGE");
            // rs.updateInt(3, 5);
            // rs.insertRow();

        %>
    </body>
</html>
