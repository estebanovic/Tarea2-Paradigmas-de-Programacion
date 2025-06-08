/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Opcion;
import Models.Pregunta;
import Models.Prueba;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esteban
 */
public class PruebaController {

    private Conexion conexion = new Conexion();
    private PreguntaController preguntaController = new PreguntaController();

    public boolean guardarPrueba(Prueba prueba) {
        String sql = "INSERT INTO pruebas (nombre, fecha) VALUES (?, ?)";

        try (Connection con = conexion.Connect()) {
            con.setAutoCommit(false);

            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, prueba.getNombre());
            stmt.setDate(2, new java.sql.Date(prueba.getFecha().getTime()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int pruebaId = rs.getInt(1);

                for (Pregunta p : prueba.getPreguntas()) {
                    preguntaController.guardarPregunta(p, pruebaId, con); // ← delegación
                }

                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Prueba> listarPruebas() {
        List<Prueba> lista = new ArrayList<>();
        String sql = "SELECT * FROM pruebas";

        try (Connection con = conexion.Connect(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prueba p = new Prueba();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setFecha(rs.getDate("fecha"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Prueba cargarPruebaCompleta(int pruebaId) {
        Prueba prueba = null;
        String sql = "SELECT * FROM pruebas WHERE id = ?";

        try (Connection con = conexion.Connect()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, pruebaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                prueba = new Prueba();
                prueba.setId(rs.getInt("id"));
                prueba.setNombre(rs.getString("nombre"));
                prueba.setFecha(rs.getDate("fecha"));

                // Llamar al controlador de preguntas
                List<Pregunta> preguntas = preguntaController.obtenerPreguntasDePrueba(pruebaId, con);
                for (Pregunta p : preguntas) {
                    prueba.agregarPregunta(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prueba;
    }
}
