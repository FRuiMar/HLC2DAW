/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author diurno
 */
@WebServlet(name = "ServletMatriz", urlPatterns = {"/ServletMatriz"})
public class ServletMatriz extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String numeroStr = request.getParameter("numero");
        int n = Integer.parseInt(numeroStr);
        
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
            out.println("<button onclick=\"window.location.href='index.jsp'\">Volver a la Página Inicial</button>");
            out.println("</body>");
            out.println("</html>");
        }
    }
        
    private int[][] crearMatrizMagica(int n) {  //recibe un número sólo. Porque es cuadrada. 
        int[][] matriz = new int[n][n];   //Es el mismo numero en los dos por ser cuadrada 3x3, 5x5, etc.
        int num = 1;
        int i = 0, j = n / 2;  // j va a ser la longitud de la matriz entre 2, que redondea, 
                               //quedandose en la mitad. 5/2 = 2.5 = 2 que es donde pondremos siempre el primer número, el 1.

        while (num <= n * n) { // ponemos un limite al numero que vamos a ir poniendo que no exceda las dimensiones de la matriz.
            matriz[i][j] = num;
            num++;
            i--;
            j++;

            if (i < 0 && j >= n) {
                i += 2;
                j--;
            } else {
                if (i < 0) {
                    i = n - 1;
                }
                if (j >= n) {
                    j = 0;
                }
                if (matriz[i][j] != 0) {
                    i += 2;
                    j--;
                }
            }
        }
        return matriz;
    }    
                
}
