package model;

import java.util.ArrayList;

/**
 * 
 * @author Fran Ruiz
 */
public class ListaUsuarios extends ArrayList<Usuario>{
    
    /**
     * Constructor
     */
    public ListaUsuarios() {
        /*
        Agregamos una lista de usuarios predeterminados.
        Usaremos el ArrayList para realizar una verificación
        de credenciales.
        */
        this.add(new Usuario("fran", "1234"));
        this.add(new Usuario("juan", "queso"));
        this.add(new Usuario("carlos", "pwd123"));
    }
}
