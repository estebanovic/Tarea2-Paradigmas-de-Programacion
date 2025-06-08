/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Opcion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Opcion> obtenerOpcionesDePregunta(int preguntaId, Connection con) {
        List<Opcion> opciones = new ArrayList<>();
        String sql = "SELECT * FROM opciones WHERE pregunta_id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, preguntaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Opcion o = new Opcion();
                o.setId(rs.getInt("id"));
                o.setTexto(rs.getString("texto"));
                o.setEsCorrecta(rs.getBoolean("es_correcta"));
                opciones.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return opciones;
    }
}
