/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.DBmodelo.empleadoDB;
import uniquejewerlydesings.modelo.empleado;
import uniquejewerlydesings.vista.VistaListaEmple;

/**
 *
 * @author LENOVO
 */
public class empleadoCrudControl {

    private final empleadoDB modelo;
    private final VistaListaEmple vistaEmple;
    DefaultTableModel modeloTab;

    public empleadoCrudControl(empleadoDB modelo, VistaListaEmple vistaEmple) {
        this.modelo = modelo;
        this.vistaEmple = vistaEmple;
    }

    public void iniciarControl() {
        cargarLista();
        vistaEmple.setVisible(true);
    }

    public void cargarLista() {
        modelo.listaEmpleados();
    }

}
