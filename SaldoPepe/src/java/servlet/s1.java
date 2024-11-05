package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ListaUsuarios;
import model.Usuario;

/**
 *
 * @author FRANRUIZ
 */
@WebServlet(name = "s1", urlPatterns = {"/s1"})
public class s1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            
               
            // Recogemos el usuario y password introducidos en el index.
            String user = request.getParameter("user");   
            String pwd = request.getParameter("pwd");
            
            
            /*
            Creamos un objeto Usuario con dicha información
            para usarlo en el método de verificación (verifyUser).
            */
            Usuario u = new Usuario(user, pwd);
            
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>WA - Lista de Usuarios (Servlet s1)</title>");
            out.println("<style>p {font-size: 1.5em;}</style>");                        
            out.println("</head>");
            out.println("<body>");
            
            
            HttpSession my_session = request.getSession();
            out.println("<h1>Comprobación de credenciales</h1>");

            if(verifyUser(u)){
                my_session.setAttribute("user", u);
                request.getRequestDispatcher("autenticado.jsp").forward(request, response);
            } else{
                my_session.setAttribute("user", null);
                my_session.setAttribute("user", null);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                
            }
            

            
            // Verificamos si existe el usuario en la Lista de Usuarios.
           /* if (verifyUser(u)) {
                out.println("<p>Bienvenido, " + u.getUsuario() + "!</p>");
                
                // Establecemos atributo para enviar los datos del usuario autentificado
                // al fichero autenticado.
                
                HttpSession mysession=request.getSession();
                mysession.setAttribute("user", u);
                request.getRequestDispatcher("autenticado.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje", "<p>Usuario incorrecto!</p>");  //aquí seteo un mensaje que recibire en el formulario del index.
                request.getRequestDispatcher("index.jsp").forward(request, response);
                // out.println("<p>Usuario Incorrecto!</p>");
            }*/
            
            
            
            
            
//            out.println("<form action=\"index.jsp\">");
//            out.println("<button type\"submit\">Go Back!</button>");
//            out.println("</form>");             
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    /**
     * Método que verifica si las credenciales introducidas por un usuario
     * coinciden con las existentes en una lista de usuarios (ListaUsuarios.java)
     */
    private boolean verifyUser(Usuario u) {
        // Inicializamos la lista de usuarios preestablecidos.
        ListaUsuarios lista = new ListaUsuarios();
        
        // Recorremos la lista en busca de una coincidencia.
        for (Usuario user : lista) {
            // Si existe coincidencia, devolvemos true.
            if (u.getUsuario().equals(user.getUsuario())
                    && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        // Llegados a este punto, no existe coincidencia.
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
