/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.modelo;

import java.util.Date;

/**
 *
 * @author corin
 */
public class material {

    private int id_materia;
    private String descripcion;
    private double peso;
    private double precio;
    private Date fecha_compra;

    public material() {
    }

    public material(int id_materia, String descripcion, double peso, double precio, Date fecha_compra) {
        this.id_materia = id_materia;
        this.descripcion = descripcion;
        this.peso = peso;
        this.precio = precio;
        this.fecha_compra = fecha_compra;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

}
