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
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.ConnMysql;

/**
 *
 * @author FMHJ97
 */
@WebServlet(name = "s3", urlPatterns = {"/s3"})
public class s3 extends HttpServlet {

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

            HttpSession my_session = request.getSession();

            // Si hemos entrado sin hacer login.
            if (my_session.getAttribute("admin") == null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            // Si pulsamos sobre Cancelar.
            if (request.getParameter("cancelar") != null) {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }

            // Si hemos pulsado sobre Actualizar.
            if (request.getParameter("actualizar") != null) {
                // Obtenemos los parámetros introducidos por el usuario.
                int id_registro = Integer.parseInt(request.getParameter("actualizar"));
                int eq_local = Integer.parseInt(request.getParameter("eq_local"));
                int eq_visit = Integer.parseInt(request.getParameter("eq_visit"));
                int goles_local = Integer.parseInt(request.getParameter("goles_local"));
                int goles_visit = Integer.parseInt(request.getParameter("goles_visit"));

                // Comprobamos si los dos equipos son iguales (mismo id).
                // En caso afirmativo, lanzamos mensajer de error.
                if (eq_local == eq_visit) {
                    my_session.setAttribute("msg2", "No puede enfrentarse un equipo consigo mismo!");
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    return;
                }

                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos la consulta.
                    String sql = "SELECT * FROM partido WHERE id = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    if (rs.next()) {
                        rs.absolute(1);
                        // Procedemos a realizar el update.
                        // Para no complicarse, indicar nombre columnas.
                        rs.updateInt("e1", eq_local);
                        rs.updateInt("e2", eq_visit);
                        rs.updateInt("g1", goles_local);
                        rs.updateInt("g2", goles_visit);
                        // Insertamos el nuevo registro.
                        rs.updateRow();
                    }
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a la página anterior.
                    my_session.removeAttribute("msg2");
                    my_session.removeAttribute("registro");
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
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