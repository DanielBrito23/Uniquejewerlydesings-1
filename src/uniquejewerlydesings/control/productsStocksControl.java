/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.DBmodelo.productoDB;
import uniquejewerlydesings.extra.PlaceHolder;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.producto;
import uniquejewerlydesings.vista.ListaProductos;

/**
 *
 * @author LENOVO
 */
public class productsStocksControl {

    private final productoDB modelo;
    private final ListaProductos vista;
    DefaultTableModel modeloTab;

    public productsStocksControl(productoDB modelo, ListaProductos vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciarControl() {

        //botones
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getBtnDelete().addActionListener(e -> eliminar());
        //inicia metodos
        ventana();
        cargarLista();
        placeHolder();
    }

    private void cargarLista() {
        int canFilas = vista.getTablaProductos().getRowCount();
        for (int i = canFilas - 1; i >= 0; i--) {
            modeloTab.removeRow(i);
        }
        modeloTab = (DefaultTableModel) vista.getTablaProductos().getModel();
        List<producto> lista;
        //  modelo.setIdpersona(vista.getTxtBuscar().getText());
        try {
            lista = modelo.listaProductos();
            int columnas = modeloTab.getColumnCount();
            for (int i = 0; i < lista.size(); i++) {
                modeloTab.addRow(new Object[columnas]);
                vista.getTablaProductos().setValueAt(lista.get(i).getId_producto(), i, 0);
                vista.getTablaProductos().setValueAt(lista.get(i).getCalculo_utilidad(), i, 1);
                vista.getTablaProductos().setValueAt(lista.get(i).getDescripcion(), i, 2);
                vista.getTablaProductos().setValueAt(lista.get(i).getFecha(), i, 3);
                vista.getTablaProductos().setValueAt(lista.get(i).getCantidad(), i, 4);
                vista.getTablaProductos().setValueAt(lista.get(i).getPeso_metal(), i, 5);
                vista.getTablaProductos().setValueAt(lista.get(i).getTipo_metal(), i, 6);
                vista.getTablaProductos().setValueAt(lista.get(i).getPrecio_unitario(), i, 7);
            }
            vista.getLbCantidad().setText("Cargados: " + lista.size() + " registros");

        } catch (Exception ex) {
            System.out.println("Error en el buscar control: " + ex.getMessage());
        }
    }

    public void buscar() {
        int canFilas = vista.getTablaProductos().getRowCount();
        for (int i = canFilas - 1; i >= 0; i--) {
            modeloTab.removeRow(i);
        }
        modeloTab = (DefaultTableModel) vista.getTablaProductos().getModel();
        List<producto> lista;
        //  modelo.setIdpersona(vista.getTxtBuscar().getText());
        try {
            lista = modelo.buscar(vista.getTxtBuscar().getText());
            int columnas = modeloTab.getColumnCount();
            for (int i = 0; i < lista.size(); i++) {
                modeloTab.addRow(new Object[columnas]);
                vista.getTablaProductos().setValueAt(lista.get(i).getId_producto(), i, 0);
                vista.getTablaProductos().setValueAt(lista.get(i).getCalculo_utilidad(), i, 1);
                vista.getTablaProductos().setValueAt(lista.get(i).getDescripcion(), i, 2);
                vista.getTablaProductos().setValueAt(lista.get(i).getFecha(), i, 3);
                vista.getTablaProductos().setValueAt(lista.get(i).getCantidad(), i, 4);
                vista.getTablaProductos().setValueAt(lista.get(i).getPeso_metal(), i, 5);
                vista.getTablaProductos().setValueAt(lista.get(i).getTipo_metal(), i, 6);
                vista.getTablaProductos().setValueAt(lista.get(i).getPrecio_unitario(), i, 7);
            }
            vista.getLbCantidad().setText("Cargados: " + lista.size() + " registros");

        } catch (Exception ex) {
            System.out.println("Error en el buscar control: " + ex.getMessage());
        }
    }

    private void eliminar() {
        int fsel = vista.getTablaProductos().getSelectedRow();
        if (fsel == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel modeloTabla = (DefaultTableModel) vista.getTablaProductos().getModel();
            String cod = modeloTabla.getValueAt(vista.getTablaProductos().getSelectedRow(), 0).toString();
            modelo.setId_producto(Integer.parseInt(cod));
            System.out.println(cod);
            try {
                modelo.eliminar();
                JOptionPane.showMessageDialog(null, "Dato borrado correctamente");
                cargarLista();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Dato no borrado");
            }
        }
    }

    public void ventana() {
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setTitle("Products Stocks");
    }

    public void placeHolder() {
        PlaceHolder txtbuscar = new PlaceHolder("Buscar", vista.getTxtBuscar());
    }
}
