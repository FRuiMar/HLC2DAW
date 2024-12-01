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
import model.ConnMysql;
import java.sql.*;

/**
 *
 * @author CreedH00d
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

            // Si hemos pulsado sobre Cerrar ó hemos entrado sin hacer login.
            if (my_session.getAttribute("admin") == null
                    || request.getParameter("cerrar") != null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            // Si hemos pulsado sobre Editar.
            if (request.getParameter("editar") != null) {
                // Obtenemos el id del registro seleccionado.
                int id_registro = Integer.parseInt(request.getParameter("editar"));

                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                    // Creamos el query
                    String sql = "SELECT t1.id, t1.id_local, t1.local, t1.g1, t1.g2, eq.nombre 'visitante', eq.id 'id_local' "
                            + "FROM equipo eq JOIN "
                            + "(SELECT p.id, eq.nombre 'local', eq.id 'id_local', p.g1, p.g2, p.e2 'visit' FROM partido p JOIN equipo eq WHERE p.e1 = eq.id) t1 "
                            + "WHERE eq.id = t1.visit AND t1.id = " + id_registro;

                    ResultSet rs = instruccion.executeQuery(sql);
                    if (rs.next()) {
                        // Guarda los datos en un array
                        Object[] registro = new Object[7];
                        registro[0] = rs.getInt(1);             // ID Registro
                        registro[1] = rs.getInt(2);             // ID Equipo local
                        registro[2] = rs.getString(3);          // Nombre equipo local
                        registro[3] = rs.getInt(4);             // Goles euipo local
                        registro[4] = rs.getInt(5);             // Goles equipo visitante
                        registro[5] = rs.getString(6);          // Nombre equipo visitante
                        registro[6] = rs.getInt(7);             // ID Equipo visitante

                        // Guarda el array en la sesión
                        my_session.setAttribute("registro", registro);
                    }
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a la página anterior.
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Si hemos pulsado sobre Insertar.
            if (request.getParameter("insertar") != null) {
                // Obtenemos los parámetros introducidos por el usuario.
                int eq_local = Integer.parseInt(request.getParameter("eq_local"));
                int eq_visit = Integer.parseInt(request.getParameter("eq_visit"));
                int goles_local = Integer.parseInt(request.getParameter("goles_local"));
                int goles_visit = Integer.parseInt(request.getParameter("goles_visit"));

                // Comprobamos si los dos equipos son iguales (mismo id).
                // En caso afirmativo, lanzamos mensajer de error.
                if (eq_local == eq_visit) {
                    my_session.setAttribute("msg2", "No puede enfrentarse un equipo consigo mismo!");
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }

                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos la consulta.
                    String sql = "SELECT * FROM partido";
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Procedemos a realizar la inserción.
                    rs.moveToInsertRow();
                    // La primary key es auto_increment => omitimos.
                    rs.updateInt(2, eq_local);
                    rs.updateInt(3, eq_visit);
                    rs.updateInt(4, goles_local);
                    rs.updateInt(5, goles_visit);
                    // Insertamos el nuevo registro.
                    rs.insertRow();
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a la página anterior.
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Si hemos pulsado sobre Borrar.
            if (request.getParameter("borrar") != null) {
                // Obtenemos el id del registro seleccionado.
                int id_registro = Integer.parseInt(request.getParameter("borrar"));

                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();
                    // Creamos un objeto Statement
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // Creamos la consulta.
                    String sql = "SELECT * FROM partido WHERE ID = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    // Seleccionamos el registro obtenido y borramos.
                    rs.absolute(1);
                    rs.deleteRow();
                    // Cerrar cada uno de los objetos utilizados
                    rs.close();
                    instruccion.close();
                    conn.close();
                    // Redirigimos a la página anterior.
                    my_session.removeAttribute("msg2");
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