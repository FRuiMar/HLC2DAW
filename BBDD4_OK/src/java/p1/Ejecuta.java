package p1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejecuta {

    Connection conexion;
    Statement instruccion;
    ResultSet result;

    public Ejecuta(String sentenciasql) {
//Cadena de conexion de MySql, el parametro useSSL es opcional
        String url = "jdbc:mysql://localhost:3306/hlc_crud?autoReconnect=true&useSSL=false";
// Cargamos el driver de mysql
        try {
            Class.forName("com.mysql.jdbc.Driver");
// Creamos el objeto conexion
            this.conexion = (Connection) DriverManager.getConnection(url, "dwes", "abc123.");
// Creamos un objeto Statement
            this.instruccion = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
// Creamos el query
// String sql = "select id_persona, nombre, apellido from persona";
            this.result = instruccion.executeQuery(sentenciasql);
// Cerrar cada uno de los objetos utilizados
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResult() {
        return this.result;
    }

    public void cerrar() {
        try {
            this.result.close();
            this.instruccion.close();
            this.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Ejecuta.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
}
