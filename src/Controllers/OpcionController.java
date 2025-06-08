/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Opcion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 *
 * @author Esteban
 */
public class OpcionController {
     public void guardarOpcion(Opcion opcion, int preguntaId, Connection con) throws SQLException {
        String sql = "INSERT INTO opciones (pregunta_id, texto, es_correcta) VALUES (?, ?, ?)";
         PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, preguntaId);
        stmt.setString(2, opcion.getTexto());
        stmt.setBoolean(3, opcion.isEsCorrecta());
        stmt.executeUpdate();
    }
}
