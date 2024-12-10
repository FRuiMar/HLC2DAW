package servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cierra la sesión
        HttpSession session = request.getSession();
        session.invalidate();

        // Redirige al login
        response.sendRedirect("index.jsp");
    }
}
