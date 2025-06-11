/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Esteban
 */
public class Prueba {

    private int id;
    private String nombre;
    private Date fecha;
    private List<Pregunta> preguntas = new ArrayList<>();

    public Prueba() {
    }

    public Prueba(int id, String nombre, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
    
    public int getCantidadPreguntas() {
        return preguntas.size();
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void agregarPregunta(Pregunta p) {
        preguntas.add(p);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("📄 Prueba ID: ").append(id)
                .append(", Nombre: ").append(nombre)
                .append(", Fecha: ").append(fecha).append("\n");

        int numPregunta = 1;
        for (Pregunta pregunta : preguntas) {
            sb.append("  ").append(numPregunta++).append(". ❓ ")
                    .append(pregunta.getEnunciado()).append(" [").append(pregunta.getTipo()).append(" - ").append(pregunta.getNivelBloom()).append("]\n");

            char letra = 'a';
            for (Opcion opcion : pregunta.getOpciones()) {
                sb.append("     ").append(letra++).append(") ").append(opcion.getTexto());
                if (opcion.isEsCorrecta()) {
                    sb.append(" ✅");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
