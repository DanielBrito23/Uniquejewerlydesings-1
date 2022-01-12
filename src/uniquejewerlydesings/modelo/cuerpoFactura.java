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
    

    public cuerpoFactura() {
    }

    public cuerpoFactura(int id_cuerpo, double total, double abono, double valor_pendiente, int id_encabezado, int id_cliente, int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_encabezado, id_cliente, id_persona, cedula, nombres, direccion, telefono, correo);
        this.id_cuerpo = id_cuerpo;
        this.total = total;
        this.abono = abono;
        this.valor_pendiente = valor_pendiente;
    }

   

    public int getId_cuerpo() {
        return id_cuerpo;
    }

    public void setId_cuerpo(int id_cuerpo) {
        this.id_cuerpo = id_cuerpo;
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
