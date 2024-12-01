<%-- 
    Document   : admin
    Created on : 30 nov 2024, 18:43:22
    Author     : Fran Ruiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ConnMysql"%>
<%@page import="java.sql.*"%>

<%
    if (session.getAttribute("admin") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin - CRUD (Liga Fútbol)</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            * {
                box-sizing: border-box;
            }
            .row {
                text-align: center
            }
            .formulario {
                display: flex;
                flex-direction: row;
                gap: 2em;
            }
            .form-control {
                width: auto;
            }
            a {
                text-align: start;
            }
            span {
                text-align: start;
                color: red;
                font-size: 1.2rem;
                padding-top: 1em;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1 class="my-5">Admin - CRUD (Liga Fútbol)</h1>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"></th>
                            <th scope="col">Equipo Local</th>
                            <th scope="col">Goles Local</th>
                            <th scope="col">Goles Visitante</th>
                            <th scope="col">Equipo Visitante</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            try {
                                // Creamos el objeto conexion
                                Connection conn = new ConnMysql().getConnection();
                                // Creamos un objeto Statement
                                Statement instruccion = conn.createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                // Creamos el query
                                String sql = "SELECT t1.id, t1.id_local, t1.local, t1.g1, t1.g2, eq.nombre 'visitante', eq.id 'id_local' "
                                        + "FROM equipo eq JOIN "
                                        + "(SELECT p.id, eq.nombre 'local', eq.id 'id_local', p.g1, p.g2, p.e2 'visit' FROM partido p JOIN equipo eq WHERE p.e1 = eq.id) t1 "
                                        + "WHERE eq.id = t1.visit";

                                ResultSet rs = instruccion.executeQuery(sql);
                                // Creamos una fila por cada registro.
                                int cont = 1;
                                while (rs.next()) {
                                    out.println("<tr>");
                                    out.println("<form action='s2' method='POST'>");
                                    out.println("<th scope='row'>" + cont + "</th>");
                                    out.println("<td><img src='./assets/" + rs.getInt(2) + ".png'></td>");
                                    out.println("<td>" + rs.getString(3) + "</td>");
                                    out.println("<td>" + rs.getInt(4) + "</td>");
                                    out.println("<td>" + rs.getInt(5) + "</td>");
                                    out.println("<td>" + rs.getString(6) + "</td>");
                                    out.println("<td><img src='./assets/" + rs.getInt(7) + ".png'></td>");
                                    out.println("<td><button class='btn btn-warning' type='submit' name='editar' value='" + rs.getInt(1) + "'>Editar</td>");
                                    out.println("<td><button class='btn btn-danger' type='submit' name='borrar' value='" + rs.getInt(1) + "'>Borrar</td>");
                                    out.println("</form>");
                                    out.println("</tr>");
                                    cont++;
                                }
                                // Cerrar cada uno de los objetos utilizados
                                rs.close();
                                instruccion.close();
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        %>
                        <!-- Fila Insertar -->
                        <tr>
                    <form action="s2" method="POST">
                        <td></td>
                        <td></td>
                        <td>
                            <select class="form-select" name="eq_local">
                                <%
                                    try {
                                        // Creamos el objeto conexion
                                        Connection conn = new ConnMysql().getConnection();
                                        // Creamos un objeto Statement
                                        Statement instruccion = conn.createStatement(
                                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                        // Creamos el query
                                        String sql = "SELECT * FROM equipo";
                                        ResultSet rs = instruccion.executeQuery(sql);
                                        while (rs.next()) {
                                            out.println("<option value='" + rs.getInt(1) + "'>");
                                            out.println(rs.getString(2));
                                            out.println("</option>");
                                        }
                                        // Cerrar cada uno de los objetos utilizados
                                        rs.close();
                                        instruccion.close();
                                        conn.close();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                %>
                            </select>
                        </td>
                        <td>
                            <input type="number" name="goles_local" placeholder="Goles Equipo Local">
                        </td>
                        <td>
                            <input type="number" name="goles_visit" placeholder="Goles Equipo Visitante">
                        </td>
                        <td>
                            <select class="form-select" name="eq_visit">
                                <%
                                    try {
                                        // Creamos el objeto conexion
                                        Connection conn = new ConnMysql().getConnection();
                                        // Creamos un objeto Statement
                                        Statement instruccion = conn.createStatement(
                                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                        // Creamos el query
                                        String sql = "SELECT id, nombre FROM equipo";
                                        ResultSet rs = instruccion.executeQuery(sql);
                                        while (rs.next()) {
                                            out.println("<option value='" + rs.getInt(1) + "'>");
                                            out.println(rs.getString(2));
                                            out.println("</option>");
                                        }
                                        // Cerrar cada uno de los objetos utilizados
                                        rs.close();
                                        instruccion.close();
                                        conn.close();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                %>
                            </select>                            
                        </td>
                        <td>
                            <button type="submit" class="btn btn-success" name="insertar">Insertar</button>
                        </td>
                        <td></td>
                        <td>
                            <button type="submit" class="btn btn-dark" name="cerrar">Cerrar</button>
                        </td>
                    </form>
                    </tr>
                    </tbody>
                </table>
                <%
                    // Comprobamos si existe un atributo msg en la sesión.
                    String mensaje = (String) session.getAttribute("msg2");
                    if (mensaje != null) {
                        out.println("<span>" + mensaje + "</span>");
                    }
                %>
            </div>
        </div>
    </body>
</html>