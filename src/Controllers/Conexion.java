/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Esteban
 */
public class Conexion {
    Connection con;
    String sqlUser = "root";
    String sqlPass = "root";
    String driver = "com.mysql.cj.jdbc.Driver";
    String conRoute = "jdbc:mysql://localhost:3306";
    
    public Connection Connect(){
        try {
            Class.forName(driver);
            con = (Connection)DriverManager.getConnection(conRoute, sqlUser, sqlPass);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
}
