package servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import clases.ConnMysql;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        //Recojo del input el usuario y el password
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //intento la conexión y hago el query con el prepare Statement.
        try (Connection conn = new ConnMysql().getConnection()) {
            String query = "SELECT * FROM usuario WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //de los datos, recojo el role.
                String role = rs.getString("role");

                // Crea la sesión con el nombre y el role.
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                // Entramos al foro. 
                response.sendRedirect("foro.jsp");
            } else {
                // Vuelvo al login con el error.
                response.sendRedirect("index.jsp?error=true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
