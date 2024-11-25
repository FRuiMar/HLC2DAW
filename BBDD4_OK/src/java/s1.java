/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnMysql;

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

            try {
                out.println("hello");
                if (request.getParameter("borrar") != null) {
                    String id = request.getParameter("id");
                    out.println(id);
                    ResultSet rs = new p1.Ejecuta("select * from persona where id=" + id).getResult();
                    rs.absolute(1);
                    rs.deleteRow();
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                if (request.getParameter("insert") != null) {

                    try {
                        ResultSet rs = new p1.Ejecuta("select * from persona").getResult();
                        ResultSet rs2 = new p1.Ejecuta("select * from persona order by id desc limit 1").getResult();
                        if (rs2.next()) {
                            out.println(rs2.getInt(1));
                        }
                        rs.moveToInsertRow();
                        int x = rs2.getInt(1);
                        rs.updateInt(1, x + 1);
                        rs.updateString(2, request.getParameter("nombre"));
                        rs.updateInt(3, Integer.parseInt(request.getParameter("nota")));
                        String fechaString = request.getParameter("fecha");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        java.util.Date parsedDate = sdf.parse(fechaString);  // Parse the string into a Date object
                        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                        rs.updateDate(4, sqlDate);
                        rs.insertRow();
                        request.getRequestDispatcher("index.jsp").forward(request, response);

                    } catch (Exception e) {

                    }

                }
                if (request.getParameter("editar") != null) {
                    try {
                        String id = request.getParameter("id");
                        ResultSet rs = new p1.Ejecuta("select * from persona where id=" + id).getResult();
                        rs.absolute(1);
                        rs.updateString(2, request.getParameter("nombre"));
                        rs.updateInt(3, Integer.parseInt(request.getParameter("nota")));
                        String fechaString = request.getParameter("fecha");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        java.util.Date parsedDate = sdf.parse(fechaString);  // Parse the string into a Date object
                        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                        rs.updateDate(4, sqlDate);
                        rs.updateRow();
                        request.getRequestDispatcher("index.jsp").forward(request, response);

                    } catch (Exception e) {
                    }

                }
            } catch (SQLException e) {
            }

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet s1</title>");
            out.println("</head>");
            out.println("<body>");
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
