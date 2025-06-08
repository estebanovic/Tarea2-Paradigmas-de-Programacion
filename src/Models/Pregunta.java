/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esteban
 */
public class Pregunta {

    private int id;
    private String enunciado;
    private String tipo; // "VF" o "MULTIPLE"
    private String nivelBloom;
    private List<Opcion> opciones;
    private String respuestaCorrecta;

    public Pregunta() {
        opciones = new ArrayList<>();
    }

    public Pregunta(int id, String enunciado, String tipo, String nivelBloom, List<Opcion> opciones, String respuestaCorrecta) {
        this.id = id;
        this.enunciado = enunciado;
        this.tipo = tipo;
        this.nivelBloom = nivelBloom;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivelBloom() {
        return nivelBloom;
    }

    public void setNivelBloom(String nivelBloom) {
        this.nivelBloom = nivelBloom;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public void agregarOpcion(Opcion o) {
        System.out.println("agregando opcion: " + o);
        opciones.add(o);
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("📄 Pregunta ID: ").append(id)
                .append(", Nombre: ").append(enunciado)
                .append(", Tipo: ").append(tipo)
                .append(", nivelbloom: ").append(nivelBloom).append("\n");

        char letra = 'a';
        for (Opcion opcion : opciones) {
            sb.append("     ").append(letra++).append(") ").append(opcion.getTexto());
            if (opcion.isEsCorrecta()) {
                sb.append(" ✅");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
