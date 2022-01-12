/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.DBmodelo.personaDB;
import uniquejewerlydesings.extra.PlaceHolder;
import uniquejewerlydesings.modelo.persona;

import uniquejewerlydesings.vista.ListaPersonas;
import uniquejewerlydesings.vista.PersonaIngreso;

/**
 *
 * @author LENOVO
 */
public class listaControl {

    private final personaDB modelo;
    private final ListaPersonas vista;

    DefaultTableModel modeloTab;

    public listaControl(personaDB modelo, ListaPersonas vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciaControl() {
        //botones
        vista.getBtncargar().addActionListener(e -> buscar());
        vista.getBtnDeletePer().addActionListener(e -> eliminar());
        ///////
        placeHolder();
        cargarLista();
        ventana();
    }

    private void cargarLista() {

        int canFilas = vista.getTabla().getRowCount();
        for (int i = canFilas - 1; i >= 0; i--) {
            modeloTab.removeRow(i);

        }

        modeloTab = (DefaultTableModel) vista.getTabla().getModel();
        List<persona> lista;

        try {
            lista = modelo.listaPersonas();
            int columnas = modeloTab.getColumnCount();

            for (int i = 0; i < lista.size(); i++) {
                modeloTab.addRow(new Object[columnas]);
                vista.getTabla().setValueAt(lista.get(i).getId_persona(), i, 0);
                vista.getTabla().setValueAt(lista.get(i).getCedula(), i, 1);
                vista.getTabla().setValueAt(lista.get(i).getNombres(), i, 2);
                vista.getTabla().setValueAt(lista.get(i).getDireccion(), i, 3);
                vista.getTabla().setValueAt(lista.get(i).getTelefono(), i, 4);
                vista.getTabla().setValueAt(lista.get(i).getCorreo(), i, 5);

            }
            vista.getLbltexto().setText("Cargados " + lista.size() + " registros");
        } catch (SQLException e) {
            System.out.println("Error en la tabla personas: " + e.getLocalizedMessage() + " causa: " + e.getCause());

        }

    }

    public void buscar() {
        int canFilas = vista.getTabla().getRowCount();
        for (int i = canFilas - 1; i >= 0; i--) {
            modeloTab.removeRow(i);
        }

        modeloTab = (DefaultTableModel) vista.getTabla().getModel();
        List<persona> lista;
        //  modelo.setIdpersona(vista.getTxtBuscar().getText());
        try {
            lista = modelo.buscar(vista.getTxtBuscar().getText());
            int columnas = modeloTab.getColumnCount();
            for (int i = 0; i < lista.size(); i++) {
                modeloTab.addRow(new Object[columnas]);
                vista.getTabla().setValueAt(lista.get(i).getId_persona(), i, 0);
                vista.getTabla().setValueAt(lista.get(i).getCedula(), i, 1);
                vista.getTabla().setValueAt(lista.get(i).getNombres(), i, 2);
                vista.getTabla().setValueAt(lista.get(i).getDireccion(), i, 3);
                vista.getTabla().setValueAt(lista.get(i).getTelefono(), i, 4);
                vista.getTabla().setValueAt(lista.get(i).getCorreo(), i, 5);
            }
            vista.getLbltexto().setText("Cargados: " + lista.size() + " registros");

        } catch (Exception ex) {
            System.out.println("Error en el buscar producto: " + ex.getMessage());
        }
    }

    private void eliminar() {
        int fsel = vista.getTabla().getSelectedRow();
        if (fsel == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel modeloTabla = (DefaultTableModel) vista.getTabla().getModel();
            String cod = modeloTabla.getValueAt(vista.getTabla().getSelectedRow(), 0).toString();
            modelo.setId_persona(Integer.parseInt(cod));
            System.out.println(cod);
            try {
                modelo.eliminarPersona();
                JOptionPane.showMessageDialog(null, "Dato borrado correctamente");
                cargarLista();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Dato no borrado");
            }

        }
    }

    public void placeHolder() {
        PlaceHolder txtbuscar = new PlaceHolder("Buscar", vista.getTxtBuscar());
    }

    public void ventana() {
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setTitle("List Customs");
    }
}
