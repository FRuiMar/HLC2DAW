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
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author diurno
 */
@WebServlet(name = "s1exam", urlPatterns = {"/s1exam"})
public class s1exam extends HttpServlet {

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

            //Creo la sesión
            HttpSession my_session = request.getSession();

            //recibo los datos del input index.jsp
            String user = (String) request.getParameter("user");
            String pwd = (String) request.getParameter("pwd");

            //creo la conexión.
            try {

                Connection conn = new ConnMysql().getConnection();

                Statement instruccion = conn.createStatement( //lo verde es para poder moverte por el resultset y que se actualice la posicion.
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                //creo la consulta
                String sql = "SELECT * from usuario WHERE dni = '" + user
                        + "' AND pass = '" + pwd + "'";

                //ejecuto la consulta
                ResultSet rs = instruccion.executeQuery(sql);

                if (rs.next()) { 
                    Object[] registro = new Object[4];  
                    registro[0] = rs.getString(1); 
                    registro[1] = rs.getInt(4);
                    registro[2] = rs.getString(2);
                    registro[3] = rs.getInt(3);
                    int esAdmin = (int) registro[1];
                    
                    my_session.setAttribute("login", registro);
                    
                    rs.close();
                    instruccion.close();
                    conn.close();

                    if(esAdmin == 1){
                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("conductor.jsp").forward(request, response);
                    }
                        
                    
                }


            } catch (SQLException e) {
                e.printStackTrace();
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
