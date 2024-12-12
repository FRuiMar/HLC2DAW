<%-- 
    Document   : admin
    Created on : 11 dic 2024, 12:25:54
    Author     : diurno
--%>


<%@page import="clases.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align:center;">GESTIÓN DE LOS PARTIDOS</h1>
        <form method="post" action="s3exam">
            <button type="submit" name="action" value="Cerrar" class="btn btn-success">Cerrar</button>
        </form>
        
        <!-- Tabla de partidos -->
        <table>
            <tr>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Puntos</th>
                <th></th>

            </tr>
            <%
                ConnMysql connMysql = new ConnMysql();
                Connection conn = connMysql.getConnection();

                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario"); ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        String dni1 = rs.getString(1);
                        String nombre1 = rs.getString(2);
                        String puntos1 = rs.getString(3);
                        String admin1 = rs.getString(4);
                        String pass1 = rs.getString(5);
            %>
            <tr>
                <form method="post" action="s2exam">
                    <td><%= dni1%></td>
                    <td><%= nombre1%></td>
                    <td><input type="number" name="puntos" value="<%=puntos1%>" required></td>
                    <td>
                        <input type="hidden" name="dni" value="<%= dni1%>">
                        <input type="hidden" name="name" value="<%= nombre1%>">
                        <input type="hidden" name="admin" value="<%= admin1%>">
                        <input type="hidden" name="pass" value="<%= pass1%>">
                        <button type="submit" name="action" value="Guardar" class="btn btn-warning">Guardar</button>
                    </td>
                </form>
            </tr>
        <%
                     }
        %>
        

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
