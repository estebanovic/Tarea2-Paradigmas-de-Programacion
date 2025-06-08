/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Respuesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Esteban
 */
public class RespuestaController {
    public void guardarRespuesta(Respuesta r, Connection con) throws SQLException {
        String sql = "INSERT INTO respuestas (resultado_id, pregunta_id, opcion_marcada, correcta) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, r.getResultadoId());
        stmt.setInt(2, r.getPreguntaId());
        stmt.setString(3, r.getOpcionMarcada());
        stmt.setBoolean(4, r.isCorrecta());
        stmt.executeUpdate();
    }
}

