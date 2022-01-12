/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.modelo;

/**
 *
 * @author corin
 */
public class reparaciones {

    private int id_reparacion;
    private String descripcion;
    private double valor_reparacion;

    public reparaciones() {
    }

    public reparaciones(int id_reparacion, String descripcion, double valor_reparacion) {
        this.id_reparacion = id_reparacion;
        this.descripcion = descripcion;
        this.valor_reparacion = valor_reparacion;
    }

    public int getId_reparacion() {
        return id_reparacion;
    }

    public void setId_reparacion(int id_reparacion) {
        this.id_reparacion = id_reparacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor_reparacion() {
        return valor_reparacion;
    }

    public void setValor_reparacion(double valor_reparacion) {
        this.valor_reparacion = valor_reparacion;
    }

    
}
