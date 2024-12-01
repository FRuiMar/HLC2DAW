c
<%-- 
    Document   : editar
    Created on : Nov 26, 2024, 8:47:05 PM
    Author     : FMHJ97
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ConnMysql"%>
<%@page import="java.sql.*"%>

<%
    if (session.getAttribute("admin") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    Object[] registro = (Object[]) session.getAttribute("registro");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar - CRUD (Liga Fútbol)</title>
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
                <h1 class="my-5">Editar - CRUD (Liga Fútbol)</h1>
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
                            out.println("<tr>");
                            out.println("<th scope='row'>" + 1 + "</th>");
                            out.println("<td><img src='./assets/" + registro[1] + ".png'></td>");
                            out.println("<td>" + registro[2] + "</td>");
                            out.println("<td>" + registro[3] + "</td>");
                            out.println("<td>" + registro[4] + "</td>");
                            out.println("<td>" + registro[5] + "</td>");
                            out.println("<td><img src='./assets/" + registro[6] + ".png'></td>");
                            out.println("<td></td>");
                            out.println("<td></td>");
                            out.println("</tr>");
                        %>
                        <!-- Fila Modificar -->
                        <tr>
                    <form action="s3" method="POST">
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
                            <button type="submit" class="btn btn-success" name="actualizar" value="<% out.print(registro[0]); %>">Actualizar</button>
                        </td>
                        <td></td>
                        <td>
                            <button type="submit" class="btn btn-dark" name="cancelar">Cancelar</button>
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