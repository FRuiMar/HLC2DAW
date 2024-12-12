<%-- 
    Document   : conductor
    Created on : 11 dic 2024, 12:26:26
    Author     : diurno
--%>


<%@page import="clases.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Object[] login = (Object[]) session.getAttribute("login");
    String dniUsu = (String) login[0];
    int esAdmin = (int) login[1];
    String nombreUsu = (String) login[2];
    int puntos = (int) login[3];

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align:center;">GESTIÓN DE LOS PARTIDOS</h1>

        <h2> Bienvenido <%= nombreUsu%></h2>
        <div> <input type="hidden" name="dni" value="<%= puntos%>"></div>
        <button type="submit" name="action" value="Cerrar" class="btn btn-success">Cerrar</button>
        <table>
            <tr>
                <th>Matricula</th>
                <th>Modelo</th>
                <th></th>
            </tr>
            <%
                ConnMysql connMysql = new ConnMysql();
                Connection conn = connMysql.getConnection();

                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM coche where propietario = '" + dniUsu + "'"); ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        String matricula = rs.getString(1);
                        String modelo = rs.getString(3);

            %>
            <tr>
                <form method="post" action="s2exam">
                    <td>Matricula: <%= matricula%></td>
                    <td>Modelo:<%= modelo%></td>
                    <td>
                        <input type="hidden" name="matricula" value="<%= matricula%>">
                        <input type="hidden" name="dni" value="<%= dniUsu%>">
                        <button type="submit" name="action" value="Borrar" class="btn btn-warning">Borrar</button>
                    </td>
                </form>
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
    <br><br>
    <h2>Añadir Nuevo Coche</h2>
    <table>
        <form method="post" action="s2exam">
            
        <tr>
            <th>Matricula</th>
            <th>Modelo</th>
            <th></th>
        </tr>
        
        <tr>
            <td><input type="text" name="matricula" placeholder="Introduce la matrícula" required></td>
            <td><input type="text" name="modelo" placeholder="Introduce el Modelo" required></td>
        </tr>
        <tr>
            <td>
                <input type="hidden" name="dni" value="<%= dniUsu%>">
                <button type="submit" name="action" value="Insertar" class="btn btn-success">Insertar</button>
            </td>
        </tr>
            
        </form>
        
    </table>
</body>
</html>