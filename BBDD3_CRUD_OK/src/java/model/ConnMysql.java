/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diurno
 */
public class ConnMysql {

    // Propiedades
    private static Connection conn = null;
    private String driver;
    private String url;
    private String usuario;
    private String password;

    // Constructor
    public ConnMysql() {

        String url = "jdbc:mysql://localhost:3306/1?autoReconnect=true&useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        String usuario = "alex";
        String password = "1234";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    } // Fin constructor

    // Métodos
    public Connection getConnection() {

        return conn;
    } // Fin getConnection

    public static void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnMysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
