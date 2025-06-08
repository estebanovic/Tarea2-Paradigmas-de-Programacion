/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Opcion;
import Models.Pregunta;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esteban
 */
public class PreguntaController {

    private OpcionController opcionController = new OpcionController();

    public void guardarPregunta(Pregunta pregunta, int pruebaId, Connection con) throws SQLException {
        String sql = "INSERT INTO preguntas (prueba_id, enunciado, tipo, nivel_bloom) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, pruebaId);
        stmt.setString(2, pregunta.getEnunciado());
        stmt.setString(3, pregunta.getTipo());
        stmt.setString(4, pregunta.getNivelBloom());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            int preguntaId = rs.getInt(1);

            for (Opcion o : pregunta.getOpciones()) {
                opcionController.guardarOpcion(o, preguntaId, con); // ← delegación
            }
        }
    }

    public List<Pregunta> obtenerPreguntasDePrueba(int pruebaId, Connection con) {
        List<Pregunta> preguntas = new ArrayList<>();
        String sql = "SELECT * FROM preguntas WHERE prueba_id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, pruebaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pregunta pregunta = new Pregunta();
                int preguntaId = rs.getInt("id");
                pregunta.setId(preguntaId);
                pregunta.setEnunciado(rs.getString("enunciado"));
                pregunta.setTipo(rs.getString("tipo"));
                pregunta.setNivelBloom(rs.getString("nivel_bloom"));

                // Llamar al controlador de opciones
                List<Opcion> opciones = opcionController.obtenerOpcionesDePregunta(preguntaId, con);
                pregunta.setOpciones(opciones);

                preguntas.add(pregunta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preguntas;
    }

}
