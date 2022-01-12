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
public class cliente extends persona {
    private int id_cliente;
   

    public cliente() {
    }

    public cliente(int id_cliente, int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_persona, cedula, nombres, direccion, telefono, correo);
        this.id_cliente = id_cliente;
    }  
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    
    
}
