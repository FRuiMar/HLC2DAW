import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import clases.ConnMysql;

@WebServlet("/AlumnosServlet")
public class AlumnosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // CONEXION BBDD
        ConnMysql connMysql = new ConnMysql();
        Connection conn = connMysql.getConnection();

        try {
            // Obtener acción realizada y verifico si es delete o insert
            String action = request.getParameter("action");

            if ("delete".equals(action)) {
                // Recojo los datos del id del formulario (hidden) y borro el estudiante.
                String id = request.getParameter("id");
                try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM estudiante WHERE id = ?")) {
                    stmt.setInt(1, Integer.parseInt(id));
                    stmt.executeUpdate();
                }
            } 
            
            if ("insert".equals(action)) {
                // Recojo los datos del formulario
                String nombre = request.getParameter("nombre");
                String fecha = request.getParameter("fecha");
                String nota = request.getParameter("nota");
                    //y hago la inserción.
                try (PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO estudiante (nombre, fecha, nota) VALUES (?, ?, ?)")) {
                    stmt.setString(1, nombre);
                    stmt.setString(2, fecha);
                    stmt.setInt(3, Integer.parseInt(nota));
                    stmt.executeUpdate();
                }
            } 
            
            if ("update".equals(action)) {
                // Recojo los datos del formulario
                String id = request.getParameter("id");
                String nombre = request.getParameter("nombre");
                String fecha = request.getParameter("fecha");
                String nota = request.getParameter("nota");
                    //Hago el update.
                try (PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE alumnos SET nombre = ?, fecha = ?, nota = ? WHERE id = ?")) {
                    stmt.setString(1, nombre);
                    stmt.setString(2, fecha);
                    stmt.setInt(3, Integer.parseInt(nota));
                    stmt.setInt(4, Integer.parseInt(id));
                    stmt.executeUpdate();
                }
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

        // Vuelvo al index(redirección).
        response.sendRedirect("index.jsp");
    }
}
