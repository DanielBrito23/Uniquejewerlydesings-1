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
public class producto  {
    private int id_producto;
    private int calculo_utilidad;//preguntar al tio si va eso 
    private String descripcion;
    private String fecha;
    private int cantidad;
    private double peso_metal;
    private String tipo_metal;
    private double precio_unitario;
    private String tipo_producto;

    public producto() {
    }

    public producto(int id_producto, int calculo_utilidad, String descripcion, String fecha, int cantidad, double peso_metal, String tipo_metal, double precio_unitario, String tipo_producto) {
        this.id_producto = id_producto;
        this.calculo_utilidad = calculo_utilidad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.peso_metal = peso_metal;
        this.tipo_metal = tipo_metal;
        this.precio_unitario = precio_unitario;
        this.tipo_producto = tipo_producto;
    }

    

  

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCalculo_utilidad() {
        return calculo_utilidad;
    }

    public void setCalculo_utilidad(int calculo_utilidad) {
        this.calculo_utilidad = calculo_utilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPeso_metal() {
        return peso_metal;
    }

    public void setPeso_metal(double peso_metal) {
        this.peso_metal = peso_metal;
    }

    public String getTipo_metal() {
        return tipo_metal;
    }

    public void setTipo_metal(String tipo_metal) {
        this.tipo_metal = tipo_metal;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    @Override
    public String toString() {
        return "producto{" + "id_producto=" + id_producto + ", calculo_utilidad=" + calculo_utilidad + ", descripcion=" + descripcion + ", fecha=" + fecha + ", cantidad=" + cantidad + ", peso_metal=" + peso_metal + ", tipo_metal=" + tipo_metal + ", precio_unitario=" + precio_unitario + ", tipo_producto=" + tipo_producto + '}';
    }

   
   
    
    
}
