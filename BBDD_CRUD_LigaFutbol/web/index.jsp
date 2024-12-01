<%-- 
    Document   : index
    Created on : 30 nov 2024, 17:41:49
    Author     : Fran Ruiz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ConnMysql"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index - CRUD (Liga Fútbol)</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            * {
                box-sizing: border-box;
            }
            .row {
                text-align: center;
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
                margin-bottom: 1em;
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
                <h1 class="my-5">Index - CRUD (Liga Fútbol)</h1>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Escudo</th>
                            <th scope="col">Equipo</th>
                            <th scope="col">Puntos</th>
                            <th scope="col">Victorias</th>
                            <th scope="col">Empates</th>
                            <th scope="col">Derrotas</th>
                            <th scope="col">Goles F</th>
                            <th scope="col">Goles C</th>
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
                                String sql = "SELECT * from equipo ORDER BY puntos DESC";
                                ResultSet rs = instruccion.executeQuery(sql);
                                // Creamos una fila por cada registro.
                                int cont = 1;
                                while (rs.next()) {
                                    out.println("<tr>");
                                    out.println("<th scope='row'>" + cont + "</th>");
                                    out.println("<td><img src='./assets/" + rs.getInt(1) + ".png'></td>");
                                    out.println("<td>" + rs.getString(2) + "</td>");
                                    out.println("<td>" + rs.getInt(3) + "</td>");
                                    out.println("<td>" + rs.getInt(4) + "</td>");
                                    out.println("<td>" + rs.getInt(5) + "</td>");
                                    out.println("<td>" + rs.getInt(6) + "</td>");
                                    out.println("<td>" + rs.getInt(7) + "</td>");
                                    out.println("<td>" + rs.getInt(8) + "</td>");
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
                    </tbody>
                </table>
                <%
                    // Si existe una sesión admin abierta, mostramos un botón
                    // con el que mostraremos el panel de admin.jsp
                    if (session.getAttribute("admin") != null) {
                %>
                <a href="admin.jsp"><button class="btn btn-primary">Ir a Administrador</button></a>
                <form class="formulario" action="s1" method="POST">
                    <button type="submit" name="logout" class="btn btn-dark">Cerrar sesión</button>
                </form>
                <%
                } else {
                %>
                <form class="formulario" action="s1" method="POST">
                    <input type="password" name="pwd" class="form-control" placeholder="Password">
                    <button type="submit" name="login" class="btn btn-primary">Administrador</button>
                </form>
                <%
                    }
                    // Comprobamos si existe un atributo msg en la sesión.
                    String mensaje = (String) session.getAttribute("msg");
                    if (mensaje != null) {
                        out.println("<span>" + mensaje + "</span>");
                    }
                %>
            </div>
        </div>
    </body>
</html>