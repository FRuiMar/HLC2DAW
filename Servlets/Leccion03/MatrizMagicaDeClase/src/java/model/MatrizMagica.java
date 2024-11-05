/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author diurno
 */
public class MatrizMagica {
    private int[][] matriz;
    
    /**
     * Default Constructor
     */
    public MatrizMagica() {
        super();
    }
    
    
        
    /**
     * Constructor with fields
     */
    public MatrizMagica(int n) {
        this.matriz = new int[n][n];
    
        //Variables
        
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
        
    }
    
    //GETTERS Y SETTERS
    
    public int[][] getMatriz() {
        return matriz;
    }
      
    
}
