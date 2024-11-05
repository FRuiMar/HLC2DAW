/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Fran Ruiz
 */
public class Usuario {
    private String nombre;
    private String password;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    
    // solo vamos a verificar.. de momento no vamos a incluir nuevos nombres.. así que GET Y LISTO.
    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }
}
