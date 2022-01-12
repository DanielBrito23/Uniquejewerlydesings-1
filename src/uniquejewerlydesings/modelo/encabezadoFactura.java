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
//llamar al id de empleado
public class encabezadoFactura extends cliente {

    private int id_encabezado;

    public encabezadoFactura() {
    }

    public encabezadoFactura(int id_encabezado, int id_cliente, int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_cliente, id_persona, cedula, nombres, direccion, telefono, correo);
        this.id_encabezado = id_encabezado;
    }

    

    public int getId_encabezado() {
        return id_encabezado;
    }

    public void setId_encabezado(int id_encabezado) {
        this.id_encabezado = id_encabezado;
    }
    

}
