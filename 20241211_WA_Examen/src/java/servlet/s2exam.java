/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import clases.ConnMysql;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author diurno
 */
@WebServlet(name = "s2exam", urlPatterns = {"/s2exam"})
public class s2exam extends HttpServlet {

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
       
        
        
        
          //CONEXIÓN CON LA BBDD
        ConnMysql connMysql = new ConnMysql();
        Connection conn = connMysql.getConnection();

        try {
            // Obtener la acción del formulario
            String action = request.getParameter("action");
        
            
            if ("Guardar".equals(action)) {
                String dni = request.getParameter("dni");
                String name = request.getParameter("name");
                int puntos = Integer.parseInt(request.getParameter("puntos"));
                int admin = Integer.parseInt(request.getParameter("admin"));
                String pass = request.getParameter("admin");
                

                try (PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE usuario SET nombre = ?, puntos = ?, admin = ?, pass = ? WHERE dni = ?")) {
                    stmt.setString(1, name);
                    stmt.setInt(2, puntos);
                    stmt.setInt(3, admin);
                    stmt.setString(4, pass);
                    stmt.setString(5, dni);
                    
                    stmt.executeUpdate();
                }

                response.sendRedirect(request.getContextPath() + "/admin.jsp");
                return;
            }
            
            
            if ("Borrar".equals(action)) {
                String dni = request.getParameter("dni");
                String matricula = request.getParameter("matricula");

                try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM coche WHERE propietario = ? AND matricula = ?")) {
                    stmt.setString(1, dni);
                    stmt.setString(2, matricula);
                    stmt.executeUpdate();
                }

                response.sendRedirect(request.getContextPath() + "/conductor.jsp");
                return;
            }
            
            
            
            if ("Insertar".equalsIgnoreCase(action)) {
                String matricula = request.getParameter("matricula");
                String modelo = request.getParameter("modelo");
                String dni = request.getParameter("dni");
                


                String sqlInsert = "INSERT INTO coche (matricula, propietario, modelo) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                    stmt.setString(1, matricula);
                    stmt.setString(2, dni);
                    stmt.setString(3, modelo);

                    stmt.executeUpdate();
                }
                response.sendRedirect(request.getContextPath() + "/conductor.jsp");
                return;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connMysql.cerrarConexion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        
        response.sendRedirect("conductor.jsp");
        
        
        
        
        
        
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
