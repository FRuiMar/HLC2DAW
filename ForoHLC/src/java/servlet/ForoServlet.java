package servlet;

import clases.ConnMysql;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ForoServlet")
public class ForoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        try (Connection conn = new ConnMysql().getConnection()) {
            if ("add".equals(action)) {
                // Inserta un nuevo mensaje
                String message = request.getParameter("message");
                String query = "INSERT INTO mensaje (user_id, contenido) SELECT id, ? FROM usuario WHERE username = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, message);
                ps.setString(2, username);
                ps.executeUpdate();
            } else if ("delete".equals(action)) {
                int messageId = Integer.parseInt(request.getParameter("id"));
                String query = "DELETE FROM mensaje WHERE id = ? AND user_id = (SELECT id FROM usuario WHERE username = ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, messageId);
                ps.setString(2, username);
                ps.executeUpdate();
            } else if ("edit".equals(action)) {
                int messageId = Integer.parseInt(request.getParameter("id"));
                String editedMessage = request.getParameter("editedMessage");
                String query = "UPDATE mensaje SET contenido = ? WHERE id = ? AND user_id = (SELECT id FROM usuario WHERE username = ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, editedMessage);
                ps.setInt(2, messageId);
                ps.setString(3, username);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirigir de vuelta al foro
        response.sendRedirect("foro.jsp");
    }
}
