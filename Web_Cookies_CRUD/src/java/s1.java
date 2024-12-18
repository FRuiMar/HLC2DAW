/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author diurno
 */
@WebServlet(urlPatterns = {"/s1"})
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet s1</title>");
            out.println("</head>");
            out.println("<body>");
            
            Cookie[] cookies =request.getCookies(); //creo un array de cookies, solicitando las cookies que haya... 
            
            String cName = request.getParameter("nomCookie");
            String cValue = request.getParameter("valor");
            
            String option = request.getParameter("button");
            
            switch (option) {
                case "Crear":
                    Cookie c = new Cookie(cName, cValue);  //creo la cookie, pero hay que meterla.(siempre que se pueda)
                    response.addCookie(c); //con esto metemos/añadimos la cookie. 
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                    
                case "Visualizar":
                    for (int i = 0; i < cookies.length; i++) {
                        out.println(cookies[i].getName() + " - " + cookies[i].getValue());
                        out.print("<br>");
                    }
                    out.print("<a href='index.jsp'>volver</a>");
                    break;
                    
                case "Modificar":
                    for (int i = 0; i < cookies.length; i++) {
                        if((cookies[i].getName()).equals(cName)){
                            cookies[i].setValue(cValue);
                            response.addCookie(cookies[i]);
                        }
                    }
                    break;
                    
                case "Eliminar":
                    cookies = request.getCookies();
                    for(int i = 0; i < cookies.length; i++) {
                         if((cookies[i].getName()).equals(cName)){
                            cookies[i].setMaxAge(0);
                            response.addCookie(cookies[i]);
                        }
                    }    
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                    
                    
                    
                default:
                    throw new AssertionError();
            }
            
            
            
            //out.println("<h1>Servlet s1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
