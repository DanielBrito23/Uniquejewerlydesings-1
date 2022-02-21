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
public class proveedor extends persona {

    private int id_proveedor;
    private int id_persona;

    public proveedor() {
    }

    public proveedor(String cedula, String nombres, String correo) {
        super(cedula, nombres, correo);
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

}
