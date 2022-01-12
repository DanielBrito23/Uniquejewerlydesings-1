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
// hacer un objeto de la clase
public class proveedor extends producto {

    material ma = new material();
    private int id_proveedor;
    private String nombre_proveedor;
    private String correo_proveedor;
    private String telefono;
    private String direccion;

    public proveedor() {
    }

    public proveedor(int id_proveedor, int id_producto, int calculo_utilidad, String descripcion, Date fecha, int cantidad, double peso_metal, String tipo_metal, double precio_unitario, String tipo_producto) {
        super(id_producto, calculo_utilidad, descripcion, fecha, cantidad, peso_metal, tipo_metal, precio_unitario, tipo_producto);
        this.id_proveedor = id_proveedor;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getCorreo_proveedor() {
        return correo_proveedor;
    }

    public void setCorreo_proveedor(String correo_proveedor) {
        this.correo_proveedor = correo_proveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
