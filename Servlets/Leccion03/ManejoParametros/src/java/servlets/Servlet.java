package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fran Ruiz
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException
        {   PrintWriter out = 
        res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("Método doGet no soportado en el Servlet");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        // Establecemos el tipo de contenido como HTML
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        
       
        //Leemos los parametros del formulario
        //por default el formulario es de tipo Get
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");
        
        // Imprimimos los parámetros en consola (para depuración)
        System.out.println("usuario:" + usuario);
        System.out.println("password:" + password);
        
        // Escribimos la respuesta HTML
        PrintWriter out = res.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("El parametro usuario es: " + usuario);
            out.println("<br>");
            out.println("El parametro password es: " + password);
            out.println("</body>");
            out.println("</html>");
            
            // Cerramos el flujo de salida
            out.close();
    }

}

   


