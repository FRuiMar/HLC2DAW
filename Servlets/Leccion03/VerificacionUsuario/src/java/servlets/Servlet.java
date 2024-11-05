package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
/*
 * @author Fran Ruiz
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})

public class Servlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");

        // Crear un objeto Usuario con los valores ingresados
        Usuario usuario = new Usuario(nombre, password);

        // Aquí puedes realizar la verificación con datos almacenados o un sistema de autenticación
        // Por ahora, simplemente verificamos un usuario y contraseña fijos
        if ("admin".equals(usuario.getNombre()) && "1234".equals(usuario.getPassword())) {
            response.getWriter().println("¡Bienvenido, " + usuario.getNombre() + "!");
        } else {
            response.getWriter().println("Usuario o contraseña incorrectos.");
        }
    }
}
