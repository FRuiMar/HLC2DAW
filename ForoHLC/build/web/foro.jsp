<%@ page import="java.sql.*, clases.ConnMysql" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Foro</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
            color: #333;
        }

        header {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }

        .message {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .message.admin {
            background-color: #fff8b3;
            font-weight: bold;
        }

        textarea {
            width: 100%;
            height: 80px;
            margin: 10px 0;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background-color: #45a049;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        form {
            display: inline-block;
        }
    </style>
</head>
<body>
    <header>
        <h1>Bienvenido al Foro</h1>
    </header>
    <div class="container">
        <!-- Muestro el usuario y el role (admin, usuario normal-->
        <p>Usuario: <%= session.getAttribute("username") %> - Rol: <%= session.getAttribute("role") %>  
            <a href="LogoutServlet">Cerrar Sesión</a></p>

        <!-- Formulario para agregar mensajes -->
        <form action="ForoServlet" method="post">
            <textarea name="message" required placeholder="Escribe tu mensaje..."></textarea><br>
            <button type="submit" name="action" value="add">Enviar</button>
        </form>
        <hr>

        <h2>Mensajes:</h2>
        <%
            try (Connection conn = new ConnMysql().getConnection()) {
                String query = "SELECT m.id, m.user_id, m.contenido, m.fecha, u.username, u.role FROM mensaje m JOIN usuario u ON m.user_id = u.id ORDER BY m.fecha DESC";
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String role = rs.getString("role");
                    String message = rs.getString("contenido");
                    boolean isAdmin = "admin".equals(role); //verifico si el role es admin.
                    boolean isOwner = username.equals(session.getAttribute("username")); 
        %>
                    <div class="message <%= isAdmin ? "admin" : "" %>">
                        <strong><%= username %>:</strong> <%= message %>
                        <small style="display: block; color: gray;">Publicado el: <%= rs.getTimestamp("fecha") %></small>
                        
                        <!-- Opciones: Editar o Borrar -->
                        <% if (isOwner) { %>
                            <!-- Botón para editar -->
                            <form action="ForoServlet" method="post">
                                <input type="hidden" name="id" value="<%= id %>">
                                <textarea name="editedMessage" required placeholder="Editar mensaje..."><%= message %></textarea><br>
                                <button type="submit" name="action" value="edit">Editar</button>
                            </form>
                        <% } %>
                        <% if (isOwner || "admin".equals(session.getAttribute("role"))) { %>
                            <!-- Botón para borrar -->
                            <form action="ForoServlet" method="post">
                                <input type="hidden" name="id" value="<%= id %>">
                                <button type="submit" name="action" value="delete">Borrar</button>
                            </form>
                        <% } %>
                    </div>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </div>
</body>
</html>
