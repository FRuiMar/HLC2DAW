<%-- 
    Document   : foro
    Created on : 10 dic 2024, 21:08:02
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="clases.ConnMysql"%>

<%//verifico  si hay ya una sesión con el atributo login., si no existe, te redirige al index (login).
    if (session.getAttribute("login") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // Saco el valor del atributo Login de la sesión.
    Object[] login = (Object[]) session.getAttribute("login");  //meto toda la info de la sesión en el objeto login.
    String autor = (String) login[0];
    int esAdmin = (int) login[1];

    // Id del mensaje a editar.
    int id_editar;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <h1>ForoMotos</h1>

        <div class="container">
            <div class="row">
                <div class="col header my-5 gap-5">
                    <h1>ForoMotos</h1>
                    <form action="s2" method="POST">
                        <button type="submit" name="logout" class="btn btn-dark">Cerrar sesión</button>
                    </form>
                </div>

            </div>
            <div class="row">
                <div class="col">
                    <h2>Mensajes del foro</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Mensaje</th>
                                <th scope="col">Usuario</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                try {

                                    Connection conn = new ConnMysql().getConnection();

                                    Statement instruccion = conn.createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                                    String sql = "SELECT * FROM mensaje";
                                    ResultSet rs = instruccion.executeQuery(sql);

                                    int cont = 1;
                                    while (rs.next()) {
                                        out.println("<tr>");
                                        out.println("<th scope='row'>" + cont + "</th>");
                                        out.println("<td>" + rs.getString(3) + "</td>");
                                        out.println("<td>" + rs.getString(2) + "</td>");

                                        boolean esAutor = rs.getString(2).equals(autor);
                                        if (esAutor || esAdmin == 1) {
                                            if (esAutor) {
                                                out.println("<td><button class='btn btn-warning' type='button' id='editar' value='" + rs.getInt(1) + "' data-msg='" + rs.getString(3) + "'>Editar</button></td>");
                                            } else {
                                                out.println("<td></td>"); // Sin botón de editar para el admin
                                            }
                                            out.println("<form action='s2' method='POST'>");
                                            out.println("<td><button class='btn btn-danger' type='submit' name='borrar' value='" + rs.getInt(1) + "'>Borrar</button></td>");
                                            out.println("</form>");
                                        } else {

                                            out.println("<td></td>");
                                            out.println("<td></td>");
                                        }
                                        out.println("</tr>");
                                        cont++;
                                    }

                                    rs.close();
                                    instruccion.close();
                                    conn.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <div class="col">
                    <div id="insert-msg" class="text-bg-light p-3">
                        <h2>Nuevo mensaje</h2>
                        <form action="s2" method="POST">
                            <div class="my-3 text-end">
                                <textarea class="form-control w-100" placeholder="Escriba aquí su mensaje..." rows="5" name="mensaje"></textarea>
                            </div>
                            <div class="text-end">
                                <button type="submit" class="btn btn-success" name="insertar">Insertar</button>
                            </div>
                        </form>
                    </div>
                    <div id="update-msg" class="hidden text-bg-light p-3">
                        <h2>Editar mensaje</h2>
                        <form action="s2" method="POST">
                            <div class="my-3 text-end">
                                <textarea class="form-control w-100" placeholder="Escriba aquí su mensaje..." rows="5" name="mensaje"></textarea>
                            </div>
                            <div class="text-end">
                                <button type="submit" class="btn btn-success" value="" name="editar" id="update">Editar</button>
                                <button type="submit" class="btn btn-secondary" name="cancel">Cancelar</button>
                            </div>
                        </form>
                    </div>                    
                </div>
            </div>
        </div>
        

    </body>
</html>
