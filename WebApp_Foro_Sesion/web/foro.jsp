<%-- 
    Document   : index
    Created on : 8 dic 2024, 14:52:09
    Author     : Fran Ruiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="clases.ConnMysql"%>

<%
    if (session.getAttribute("login") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // Saco el valor del atributo Login de la sesión.  (revisar: int id_editar;)
    Object[] login = (Object[]) session.getAttribute("login");
    String autor = (String) login[0];
    int esAdmin = (int) login[1];


%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foro CRUD</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            /* Estilos Generales */
            body {
                background-color: #f8f9fa;
                font-family: 'Arial', sans-serif;
            }

            .container {
                max-width: 1200px;
                margin-top: 30px;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .title {
                color: #4A90E2;
                font-size: 36px;
                font-weight: bold;
            }

            /* Estilo de los contenedores de mensajes */
            .msg-container {
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
                padding: 20px;
                margin-bottom: 30px;
            }

            /* Estilo de las filas de mensajes */
            table th, table td {
                vertical-align: middle;
            }

            table thead {
                background-color: #4A90E2;
                color: white;
            }

            table tbody tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            /* Formulario de inserción y actualización de mensajes */
            #insert-msg, #update-msg {
                background-color: #ffffff;
                border: 1px solid #e3e9f3;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }

            #update-msg.hidden {
                display: none;
            }

            textarea.form-control {
                resize: none;
                height: 120px;
                background-color: #fafafa;
                border-radius: 8px;
            }

            /* Botones */
            .btn {
                border-radius: 20px;
                font-weight: bold;
            }

            .btn-dark {
                background-color: #333;
                color: white;
            }

            .btn-dark:hover {
                background-color: #444;
            }

            .btn-danger {
                background-color: #e74c3c;
                color: white;
            }

            .btn-danger:hover {
                background-color: #c0392b;
            }

            .btn-warning {
                background-color: #f39c12;
                color: white;
            }

            .btn-warning:hover {
                background-color: #e67e22;
            }

            .btn-success {
                background-color: #2ecc71;
                color: white;
            }

            .btn-success:hover {
                background-color: #27ae60;
            }

            .text-end {
                text-align: right;
            }


            .msg-autor {
                font-weight: bold;
                background-color: #f0f8ff; /* Un color de fondo claro para el autor */
            }

            .msg-admin {
                font-weight: bold;
                background-color: #e3f2fd; /* Un color diferente para el admin */
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h1 class="title">
                    <%= esAdmin == 1 ? "Bienvenido Administrador" : "Bienvenido, " + autor%>
                </h1>
                <form action="s3" method="POST">
                    <button type="submit" name="logout" class="btn btn-dark">Cerrar sesión</button>
                </form>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <h2 class="mb-4">Mensajes del foro</h2>
                    <div class="msg-container">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Mensaje</th>
                                    <th>Usuario</th>
                                    <th></th>
                                    <th></th>
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
                                            String mensaje = rs.getString(3);
                                            String usuario = rs.getString(2);

                                            // Condicional para verificar si el mensaje es del autor o el administrador
                                            String msgClass = "";
                                            if (usuario.equals(autor)) {
                                                msgClass = "msg-autor"; // Clase CSS para el mensaje del autor
                                            } else if (esAdmin == 1 && usuario.equals("admin")) {
                                                msgClass = "msg-admin"; // Clase CSS para el mensaje del administrador
                                            }

                                            out.println("<tr>");
                                            out.println("<th>" + cont + "</th>");
                                            out.println("<td class='" + msgClass + "'>" + mensaje + "</td>");
                                            out.println("<td>" + usuario + "</td>");

                                            boolean esAutor = usuario.equals(autor);
                                            if (esAutor || esAdmin == 1) {
                                                if (esAutor) {
                                                    out.println("<td><button class='btn btn-warning' type='button' id='editar' value='" + rs.getInt(1) + "' data-msg='" + mensaje + "'>Editar</button></td>");
                                                } else {
                                                    out.println("<td></td>");
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
                </div>

                <div class="col-md-4">
                    <div id="insert-msg">
                        <h3>Nuevo mensaje</h3>
                        <form action="s2" method="POST">
                            <div class="mb-3">
                                <textarea class="form-control" placeholder="Escriba aquí su mensaje..." name="mensaje"></textarea>
                            </div>
                            <div class="text-end">
                                <button type="submit" class="btn btn-success" name="insertar">Insertar</button>
                            </div>
                        </form>
                    </div>

                    <div id="update-msg" class="hidden">
                        <h3>Editar mensaje</h3>
                        <form action="s2" method="POST">
                            <div class="mb-3">
                                <textarea class="form-control" placeholder="Escriba aquí su mensaje..." name="mensaje"></textarea>
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

        <script>
            document.addEventListener("click", (e) => {
                if (e.target.matches("#editar")) {
                    let id = parseInt(e.target.value);
                    let mensaje = e.target.dataset.msg;
                    let $botonEditar = document.getElementById("update");
                    let $divInsert = document.getElementById("insert-msg");
                    let $divEditar = document.getElementById("update-msg");
                    let $txtAreaEditar = document.querySelector("#update-msg textarea");

                    $botonEditar.value = id;
                    $txtAreaEditar.value = mensaje;
                    $divInsert.classList.add("hidden");
                    $divEditar.classList.remove("hidden");
                }
            });
        </script>

    </body>
</html>
