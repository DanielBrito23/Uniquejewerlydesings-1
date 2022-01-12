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
public class empleado extends persona {
    private int id_empleado;
    private String cargo;

    public empleado() {
    }

    public empleado(int id_empleado, String cargo, int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_persona, cedula, nombres, direccion, telefono, correo);
        this.id_empleado = id_empleado;
        this.cargo = cargo;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
    
}
