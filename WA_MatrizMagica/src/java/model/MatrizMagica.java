/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FMHJ97
 */
public class MatrizMagica {
    private int[][] matriz;
    
    /**
     * Default Constructor.
     */
    public MatrizMagica() {
        super();
    }
    
    /**
     * Constructor with fields.
     */
    public MatrizMagica(int n) {
        this.matriz = new int[n][n];
        
        // Variables que situan el primer número en la matriz.
        int i = 0, j = n/2;
        
        // Rellenamos la matriz mágica.
        for (int index = 1; index <= (Math.pow(n, 2)); index++) {
            // Situamos el número correspondiente en la matriz.
            matriz[i][j] = index;
            // Comprobamos si el número introducido es múltiplo o no de n(dimensión).
            if (index % n == 0) {
                // El próximo número a introducir irá justo debajo del número anterior.
                i++;
            } else {
                // En este caso, el próximo número estará situado un lugar
                // a la izquierda (j--) y una posición arriba (i--).
                j--; i--;
                // A continuación, comprobamos si la i sale de los límites.
                if (i < 0) {
                    // En caso afirmativo, la fila pasará a ser la última fila.
                    i = n - 1;
                }
                // A continuación, comprobamos si la j sale de los límites.
                if (j < 0) {
                    // En caso afirmativo, la columna pasará a ser la última columna.
                    j = n - 1;
                }                
            }
        }
    }

    /// GETTERS & SETTERS
    
    public int[][] getMatriz() {
        return matriz;
    }
}
