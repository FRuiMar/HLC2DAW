/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MatrizMagica;

/**
 *
 * @author diurno
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet</title>");
            
            // Estilos CSS para centrar la tabla
            out.println("<style>");
            out.println("table { margin: auto; border-collapse: collapse; }"); // Centrar la tabla
            out.println("td {font-size:2em;}");
            out.println("td { border: 1px solid black; padding: 10px; text-align: center; }"); // Bordes y padding
            out.println("h1 { text-align: center; }"); // Centrar el título
            out.println("button { display: block; margin: 20px auto; }"); // Estilo para el botón
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
                  
            
            
                int numero = Integer.parseInt(request.getParameter("num"));
            
                    if (numero%2 == 0 || numero < 1) {
                        request.getRequestDispatcher("index.jsp").forward( request, response);
                    } else {
                
                        int[][] matriz = new MatrizMagica(numero).getMatriz();
            
                        out.println("<h1>Matriz Mágica de tamaño " + matriz.length + "x" + matriz.length + "</h1>");
                        out.println("<table>");
                        for(int i=0;i<matriz[i].length; i++){
                            out.println("<tr>");
                            for(int j=0; j<numero;j++ )
                                out.println("<td>" + matriz[i][j] + "</td>");
                        }
                        out.println("</tr>");
                    }
               
            out.println("</table>");   
            
            out.println("<form action=' ' method='post'>"); 
                out.println("<button type=' ' method='post'>");   
                out.println("<input type='number' name='num' value='numMas'>");
                out.println("<button type='submit' name='generar'>Generar Matriz</button>");  
            out.println("</form>");


            
            
            
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
