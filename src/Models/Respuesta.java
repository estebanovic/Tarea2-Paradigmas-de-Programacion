/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Esteban
 */
public class Respuesta {
    private int id;
    private int resultadoId;
    private int preguntaId;
    private String opcionMarcada;
    private boolean correcta;

    public Respuesta() {
    }

    public Respuesta(int id, int resultadoId, int preguntaId, String opcionMarcada, boolean correcta) {
        this.id = id;
        this.resultadoId = resultadoId;
        this.preguntaId = preguntaId;
        this.opcionMarcada = opcionMarcada;
        this.correcta = correcta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResultadoId() {
        return resultadoId;
    }

    public void setResultadoId(int resultadoId) {
        this.resultadoId = resultadoId;
    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getOpcionMarcada() {
        return opcionMarcada;
    }

    public void setOpcionMarcada(String opcionMarcada) {
        this.opcionMarcada = opcionMarcada;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }
    
    
}
