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
/// hacer que el mismo id de persona se guarde en el id de cliente 
public class cuerpoFactura extends encabezadoFactura {

    private int id_cuerpo;
    private double total;
    private double abono;
    private double valor_pendiente;
    private String reparacion;
    private double total_reparacion;
    private producto producto;

    public cuerpoFactura() {
    }

    public cuerpoFactura(int id_cuerpo, double total, double abono, double valor_pendiente, String reparacion, double total_reparacion, producto producto, int id_encabezado, int id_cliente, int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_encabezado, id_cliente, id_persona, cedula, nombres, direccion, telefono, correo);
        this.id_cuerpo = id_cuerpo;
        this.total = total;
        this.abono = abono;
        this.valor_pendiente = valor_pendiente;
        this.reparacion = reparacion;
        this.total_reparacion = total_reparacion;
        this.producto = producto;
    }

    public producto getProducto() {
        return producto;
    }

    public void setProducto(producto producto) {
        this.producto = producto;
    }

    public int getId_cuerpo() {
        return id_cuerpo;
    }

    public void setId_cuerpo(int id_cuerpo) {
        this.id_cuerpo = id_cuerpo;
    }

    public String getReparacion() {
        return reparacion;
    }

    public void setReparacion(String reparacion) {
        this.reparacion = reparacion;
    }

    public double getTotal_reparacion() {
        return total_reparacion;
    }

    public void setTotal_reparacion(double total_reparacion) {
        this.total_reparacion = total_reparacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public double getValor_pendiente() {
        return valor_pendiente;
    }

    public void setValor_pendiente(double valor_pendiente) {
        this.valor_pendiente = valor_pendiente;
    }

}
