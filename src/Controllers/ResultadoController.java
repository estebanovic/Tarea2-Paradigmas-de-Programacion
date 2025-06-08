/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Opcion;
import Models.Pregunta;
import Models.Resultado;
import Models.Prueba;
import Models.Respuesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
/**
 *
 * @author Esteban
 */
public class ResultadoController {

    private Conexion conexion = new Conexion();
    private RespuestaController respuestaController = new RespuestaController();

    public void guardarResultadoConRespuestas(Resultado resultado, Prueba prueba, List<String> respuestasUsuario) {
        String sql = "INSERT INTO resultados (prueba_id, fecha, nota) VALUES (?, ?, ?)";

        try (Connection con = conexion.Connect()) {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, resultado.getPruebaId());
            stmt.setTimestamp(2, new Timestamp(resultado.getFecha().getTime()));
            stmt.setDouble(3, resultado.getNota());
            stmt.executeUpdate();

            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int resultadoId = rs.getInt(1);

                // Guardar cada respuesta
                for (int i = 0; i < prueba.getPreguntas().size(); i++) {
                    Pregunta pregunta = prueba.getPreguntas().get(i);
                    String respuestaUsuario = respuestasUsuario.get(i);

                    boolean correcta = false;
                    for (Opcion o : pregunta.getOpciones()) {
                        if (o.getTexto().equals(respuestaUsuario) && o.isEsCorrecta()) {
                            correcta = true;
                            break;
                        }
                    }

                    Respuesta r = new Respuesta();
                    r.setResultadoId(resultadoId);
                    r.setPreguntaId(pregunta.getId());
                    r.setOpcionMarcada(respuestaUsuario);
                    r.setCorrecta(correcta);

                    respuestaController.guardarRespuesta(r, con);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
