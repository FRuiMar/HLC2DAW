package servlets;

import modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    private List<Usuario> usuarios; // Lista para almacenar los usuarios

    @Override
    public void init() throws ServletException {
        // Inicializamos la lista de usuarios con algunos datos de prueba
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin", "1234"));
        usuarios.add(new Usuario("user1", "pass1"));
        usuarios.add(new Usuario("user2", "pass2"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");

        // Variable para verificar si las credenciales son correctas
        boolean isValidUser = false;

        // Verificamos si el usuario existe en la lista
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getPassword().equals(password)) {
                isValidUser = true;
                break;
            }
        }
        
        //con esto mostramos una respuesta. Seteamos el tipo de contenido.
        response.setContentType("text/html;charset=UTF-8");  // intentamos escribir
        try (PrintWriter out = response.getWriter()) {
            if (isValidUser) {    
                out.println("<h1>¡Bienvenido " + nombre + "!</h1>");
            } else {
                out.println("<h1>Usuario o contraseña incorrectos</h1>");
            }
        }
    }
}
