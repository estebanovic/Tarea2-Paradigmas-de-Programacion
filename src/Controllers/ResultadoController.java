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
import java.util.ArrayList;
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

    public List<Resultado> listarResultados() {
        List<Resultado> lista = new ArrayList<>();
        String sql = """
        SELECT r.id, r.fecha, r.nota, r.prueba_id, p.nombre AS nombre_prueba
        FROM resultados r
        JOIN pruebas p ON r.prueba_id = p.id
        ORDER BY r.fecha DESC
    """;

        try (Connection con = conexion.Connect()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Resultado r = new Resultado();
                r.setId(rs.getInt("id"));
                r.setFecha(rs.getTimestamp("fecha"));
                r.setNota(rs.getDouble("nota"));
                r.setPruebaId(rs.getInt("prueba_id"));
                r.setNombrePrueba(rs.getString("nombre_prueba")); // Este campo lo agregamos
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Resultado obtenerResultadoPorId(int id) {
        Resultado resultado = null;
        String sql = "SELECT * FROM resultados WHERE id = ?";

        try (Connection con = conexion.Connect()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resultado = new Resultado();
                resultado.setId(rs.getInt("id"));
                resultado.setPruebaId(rs.getInt("prueba_id"));
                resultado.setFecha(rs.getTimestamp("fecha"));
                resultado.setNota(rs.getDouble("nota"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public void cargarRespuestasEnResultado(Resultado resultado) {
        List<Respuesta> lista = new ArrayList<>();
        String sql = "SELECT * FROM respuestas WHERE resultado_id = ?";

        try (Connection con = conexion.Connect()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, resultado.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Respuesta r = new Respuesta();
                r.setId(rs.getInt("id"));
                r.setResultadoId(rs.getInt("resultado_id"));
                r.setPreguntaId(rs.getInt("pregunta_id"));
                r.setOpcionMarcada(rs.getString("opcion_marcada"));
                r.setCorrecta(rs.getBoolean("correcta"));
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        resultado.setRespuestas(lista);
    }

}
