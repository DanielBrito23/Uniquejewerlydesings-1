/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.DBmodelo.proveedorDB;
import uniquejewerlydesings.modelo.proveedor;
import uniquejewerlydesings.vista.CrudProveedor;

/**
 *
 * @author LENOVO
 */
public class crudProveedorControl {

    private proveedorDB proveedorDB;
    private CrudProveedor vista;

    DefaultTableModel modeloTab;

    public crudProveedorControl(proveedorDB proveedorDB, CrudProveedor vista) {
        this.proveedorDB = proveedorDB;
        this.vista = vista;
    }

    public void iniciarControl() {
        ventana();
        listarProoveedores();
    }

    public void listarProoveedores() {
        proveedorDB.listaProveedores();
    }

    public void ventana() {
        vista.setVisible(true);
    }
}
