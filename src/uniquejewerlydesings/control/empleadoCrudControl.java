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
import uniquejewerlydesings.modelo.producto;
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

    private void cargarLista() {
        int canFilas = vistaEmple.getTablaEmple().getRowCount();
        for (int i = canFilas - 1; i >= 0; i--) {
            modeloTab.removeRow(i);
        }
        modeloTab = (DefaultTableModel) vistaEmple.getTablaEmple().getModel();
        List<empleado> lista;
        //  modelo.setIdpersona(vista.getTxtBuscar().getText());
        try {
            lista = modelo.listaEmpleados();
            int columnas = modeloTab.getColumnCount();
            for (int i = 0; i < lista.size(); i++) {
                modeloTab.addRow(new Object[columnas]);
                vistaEmple.getTablaEmple().setValueAt(lista.get(i).getId_empleado(), i, 0);
                vistaEmple.getTablaEmple().setValueAt(lista.get(i).getNombres(), i, 1);
                vistaEmple.getTablaEmple().setValueAt(lista.get(i).getCargo(), i, 2);

            }
            vistaEmple.getLbCantidad().setText("Cargados: " + lista.size() + " registros");

        } catch (Exception ex) {
            System.out.println("Error en el buscar control: " + ex.getMessage());
        }
    }

}
