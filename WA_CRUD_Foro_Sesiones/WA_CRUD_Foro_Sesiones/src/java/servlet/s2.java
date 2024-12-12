/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import clases.ConnMysql;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "s2", urlPatterns = {"/s2"})
public class s2 extends HttpServlet {

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
            
            HttpSession my_session = request.getSession();

            // Obtenemos el nombre (PK) del usuario logueado.
            Object[] registro = (Object[]) my_session.getAttribute("login");
            String autor = (String) registro[0];

            if (request.getParameter("logout") != null) {
                my_session.removeAttribute("login");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            if (request.getParameter("cancel") != null) {
                request.getRequestDispatcher("foro.jsp").forward(request, response);
            }

            if (request.getParameter("borrar") != null) {
                // Obtenemos el id del registro seleccionado.
                int id_registro = Integer.parseInt(request.getParameter("borrar"));

                try {
                    
                    Connection conn = new ConnMysql().getConnection();
                    
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
                    String sql = "SELECT * FROM mensaje WHERE id = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    
                    rs.absolute(1);
                    rs.deleteRow();
                    
                    rs.close();
                    instruccion.close();
                    conn.close();
                    
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("foro.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (request.getParameter("insertar") != null) {
                // Obtenemos los parámetros introducidos
                String mensaje = request.getParameter("mensaje");

                try {
                    
                    Connection conn = new ConnMysql().getConnection();
                    
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
                    String sql = "SELECT * FROM mensaje";
                    ResultSet rs = instruccion.executeQuery(sql);
                    
                    rs.moveToInsertRow();
                    
                    rs.updateString(2, autor);
                    rs.updateString(3, mensaje);
                    
                    rs.insertRow();
                    
                    rs.close();
                    instruccion.close();
                    conn.close();
                    
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("foro.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (request.getParameter("editar") != null) {
                int id_registro = Integer.parseInt(request.getParameter("editar"));
                String mensaje = request.getParameter("mensaje");

                try {
                    
                    Connection conn = new ConnMysql().getConnection();
                    
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
                    String sql = "SELECT * FROM mensaje WHERE id = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    if (rs.next()) {
                        rs.updateString("contenido", mensaje);
                        rs.updateRow();
                    }
                    
                    rs.close();
                    instruccion.close();
                    conn.close();
                    
                    request.getRequestDispatcher("foro.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

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
