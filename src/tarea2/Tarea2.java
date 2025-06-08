/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea2;

import Controllers.Conexion;
import java.sql.Connection;

/**
 *
 * @author Esteban
 */
public class Tarea2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Conectar bd sql
        Conexion con = new Conexion();
        Connection cn = con.Connect();
    }
    
}
