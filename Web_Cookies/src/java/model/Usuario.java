package model;

/**
 *
 * @author 
 */
public class Usuario {
    private String usuario;
    private String password;
    private String color;
    private String fuente;
    private int saldo;
    
    /**
     * Default Constructor.
     */
    public Usuario() {
        super();
    }

    /**
     * Constructor with fields.
     */
    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    
    public Usuario(String usuario, String password, String color, String fuente) {
        this.usuario = usuario;
        this.password = password;
        this.color = color;
        this.fuente = fuente;
        
    }
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getColor() {
        return password;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
}
