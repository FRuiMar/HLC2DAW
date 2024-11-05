package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action"); // Obtener la acción
        
        
        int n = 1; //valor por defecto.
        String numeroStr = request.getParameter("numero");  //sacamos el número del index.jsp
        if(numeroStr != null){
            n = Integer.parseInt(numeroStr);  //parseamos el string a number.
        }
        
        //Verificamos si ha sube o baja
        if ("add".equals(action)) {
            n += 2; // Aumentar tamaño
        } else if ("subtract".equals(action) && n > 1) {
            n -= 2; // Disminuir tamaño
        }
        
        
        // Verificar si el número es impar
        if (n % 2 == 0) {  // si es par lanzamos el error.
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                // Estilos CSS
                out.println("<style>");
                out.println("h1 { text-align: center; }");
                out.println("button { display: block; margin: 20px auto; }"); // Estilo para el botón
                out.println("</style>");
                out.println("</head>");
                
                out.println("<body>");
                out.println("<h1>Error: Debe ingresar un número impar.</h1>");
                out.println("<button onclick=\"window.location.href='index.jsp'\">Volver a la Página Inicial</button>");
                out.println("</body>");
                out.println("</html>");
            }
            return;
        }

        // Crear la matriz mágica
        int[][] matrizMagica = crearMatrizMagica(n);
        
        // Enviar la matriz mágica al cliente
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Matriz Mágica</title>");
            
// Estilos CSS para centrar la tabla
            out.println("<style>");
            out.println("table { margin: auto; border-collapse: collapse; }"); // Centrar la tabla
            out.println("td { font-size:2em; }"); 
            out.println("td { border: 1px solid black; padding: 10px; text-align: center; }"); // Bordes y padding
            out.println("h1 { text-align: center; }"); // Centrar el título
            
            out.println("button { display: block; margin: 20px auto; }"); // Estilo para el botón
            
            out.println("</style>");
            
            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Matriz Mágica de tamaño " + n + "x" + n + "</h1>");
            out.println("<table>");
            for (int i = 0; i < n; i++) {
                out.println("<tr>");
                for (int j = 0; j < n; j++) {
                    out.println("<td>" + matrizMagica[i][j] + "</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            
            
             // Formulario para aumentar y disminuir tamaño
            out.println("<form action='Servlet' method='post'>");
            out.println("<input type='hidden' name='numero' value='" + n + "'>");
            out.println("<button type='submit' name='action' value='add'>Aumentar 2</button>");
            out.println("<button type='submit' name='action' value='subtract'>Disminuir 2</button>");
            out.println("</form>");
            
            
            out.println("<button onclick=\"window.location.href='index.jsp'\">Volver a la Página Inicial</button>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private int[][] crearMatrizMagica(int n) {  //recibe un número sólo. Porque es cuadrada. 
        
        int[][] matriz = new int[n][n];   //Es el mismo numero en los dos por ser cuadrada 3x3, 5x5, etc.
        
        int i=0, j=n/2;

        //rellenamos matriz
        for (int index = 1; index <= (Math.pow(n,2)); index++){
            
            matriz[i][j] = index;
            
            if(index % n == 0) {
                i++;
            }else{
                j--; i--;
                if (i<0) {
                    i = n-1; 
                }
                if (j<0) {
                    j=n-1;
                }
            }
        }
        return matriz;
    }
}
