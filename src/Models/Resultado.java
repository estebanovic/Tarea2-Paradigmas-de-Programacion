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
public class Resultado {

    private int id;
    private int pruebaId;
    private Date fecha;
    private double nota;
    private List<Respuesta> respuestas = new ArrayList<>();

    public Resultado() {
    }

    public Resultado(int id, int pruebaId, Date fecha, double nota) {
        this.id = id;
        this.pruebaId = pruebaId;
        this.fecha = fecha;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPruebaId() {
        return pruebaId;
    }

    public void setPruebaId(int pruebaId) {
        this.pruebaId = pruebaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

  

}
