import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import clases.ConnMysql;

@WebServlet("/ClasificacionServlet")
public class ClasificacionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        //CONEXIÓN CON LA BBDD
        ConnMysql connMysql = new ConnMysql();
        Connection conn = connMysql.getConnection();

        try {
            // Obtener la acción del formulario
            String action = request.getParameter("action");
            
            //verifico el value del action
            if ("logear".equals(action)) {
                // Recojo la clave introducida en el input
                String clave = request.getParameter("clave");
                
                //creo una bandera para validar
                boolean claveValida = false;
                //hago otro statement para verificar el password, no hay usuario en este caso.EN ESTOS CASOS ES BUENO PARA EVITAR INYECCIONES SQL
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clave WHERE pass = ?")) {
                    stmt.setString(1, clave); //le paso la clave recibida del input.
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {  // si hay una fila con esa clave.. entonces clave valida.
                            claveValida = true;
                        }
                    }
                }

                if (claveValida) {  // si la clave es valida, redirigo a admin.jsp y pongo un return para evitar que continúe ejecutandose cualquier código.
                    response.sendRedirect(request.getContextPath() + "/admin.jsp");
                    return;
                    
                } else {
                    request.setAttribute("Error", "Clave no válida, inténtelo de nuevo.");//genero un error. que mando al index.jsp en la siguiente linea. 
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }
            }

            if ("Actualizar".equals(action)) {
                // Modifico partido.. //parseo a int para meter los datos en la base de datos. 
                int id = Integer.parseInt(request.getParameter("id"));
                int equipo1 = Integer.parseInt(request.getParameter("equipo1"));
                int equipo2 = Integer.parseInt(request.getParameter("equipo2"));
                int goles1 = Integer.parseInt(request.getParameter("goles1"));
                int goles2 = Integer.parseInt(request.getParameter("goles2"));

                try (PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE partido SET e1 = ?, e2 = ?, g1 = ?, g2 = ? WHERE id = ?")) {
                    stmt.setInt(1, equipo1);
                    stmt.setInt(2, equipo2);
                    stmt.setInt(3, goles1);
                    stmt.setInt(4, goles2);
                    stmt.setInt(5, id);
                    stmt.executeUpdate();
                }

                response.sendRedirect(request.getContextPath() + "/admin.jsp");
                return;
            }


            if ("Borrar".equals(action)) {
                // Borrar partido
                int id = Integer.parseInt(request.getParameter("id"));

                try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM partido WHERE id = ?")) {
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }

                response.sendRedirect(request.getContextPath() + "/admin.jsp");
                return;
            }

            if ("Insertar".equalsIgnoreCase(action)) {
                // Obtener los datos del formulario
                int equipo1 = Integer.parseInt(request.getParameter("equipo1"));
                int goles1 = Integer.parseInt(request.getParameter("goles1"));
                int equipo2 = Integer.parseInt(request.getParameter("equipo2"));
                int goles2 = Integer.parseInt(request.getParameter("goles2"));

                // Preparar el INSERT
                String sqlInsert = "INSERT INTO partido (e1, e2, g1, g2) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                    stmt.setInt(1, equipo1);
                    stmt.setInt(2, equipo2);
                    stmt.setInt(3, goles1);
                    stmt.setInt(4, goles2);

                    // Ejecutar el INSERT
                    stmt.executeUpdate();
                }
                response.sendRedirect(request.getContextPath() + "/admin.jsp");
                return;
            }

            
            // Redirigo  al index.jsp
            if ("Cerrar".equals(action)) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
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

        response.sendRedirect("index.jsp");
    }
}
