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
import clases.ConnMysql;
import jakarta.servlet.http.HttpSession;
import java.sql.*;

/**
 *
 * @author Usuario
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

            //Creo la sesión
            HttpSession my_session = request.getSession();

            //verifico si existe ya una sesión con atributo Login, y me rediriges ya al foro.
            if (my_session.getAttribute("login") != null) {
                request.getRequestDispatcher("foro.jsp").forward(request, response);
            }

            //recibo los datos del input index.jsp
            String user = (String) request.getParameter("user");
            String pwd = (String) request.getParameter("pwd");

            //creo la conexión.
            try {

                Connection conn = new ConnMysql().getConnection();

                Statement instruccion = conn.createStatement( //lo verde es para poder moverte por el resultset y que se actualice la posicion.
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                //creo la consulta
                String sql = "SELECT * from usuario WHERE usuario = '" + user
                        + "' AND pass = '" + pwd + "'";

                //ejecuto la consulta
                ResultSet rs = instruccion.executeQuery(sql);

                if (rs.next()) {  // si hay un registro.  rs.absolut(1)-->indica la fila.. la 1 es la primera.  
                    Object[] registro = new Object[2];  //creo un objeto array de 2  para guardar el usuario y si es admin.
                    registro[0] = rs.getString(1); //columna 1.. nombre usuario
                    registro[1] = rs.getInt(3); //columna 3.. admin o no

                    //modifico la sesion y le asigno el nombre de atributo login,  el valor, es lo que hay en el registro.(nomUsuario y admin)
                    my_session.setAttribute("login", registro);
                    
                    //cierro el resultset, las conexiones, etc.
                    rs.close();
                    instruccion.close();
                    conn.close();

                    //Accedo al foro.
                    request.getRequestDispatcher("foro.jsp").forward(request, response);
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
