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
import uniquejewerlydesings.modelo.validacion;
import uniquejewerlydesings.vista.ListaProductos;
import uniquejewerlydesings.vista.RegistroProductos;

/**
 *
 * @author LENOVO
 */
public class productsStocksControl extends validacion {
    
    private final productoDB modelo;
    private final ListaProductos vista;
    DefaultTableModel modeloTab;
    
    String idp = null;
    
    public productsStocksControl(productoDB modelo, ListaProductos vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    public void iniciarControl() {

        //botones
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getBtnDelete().addActionListener(e -> eliminar());
        
        vista.getBtnNewPro().addActionListener(e -> abrirDialogo(0));
        vista.getBtnGuardar().addActionListener(e -> nuevoProducto());
        vista.getBtnEditPro().addActionListener(e -> abrirDialogo(1));

        //inicia metodos
        //ventana();
        cargarLista();
        placeHolder();
        placeHolderFecha();
        vista.getTxtID().setText(String.valueOf(idProducto()));
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
    
    public void abrirDialogo(int b) {
        if (b == 0) {
            vista.getDlgProducto().setVisible(true);
            vista.getDlgProducto().setLocationRelativeTo(null);
            vista.getDlgProducto().setTitle("New Product");
            vista.getDlgProducto().setSize(446, 467);
        } else {
            DefaultTableModel modeloTabla = (DefaultTableModel) vista.getTablaProductos().getModel();
            int fsel = vista.getTablaProductos().getSelectedRow();
            if (fsel == -1) {
                JOptionPane.showMessageDialog(null, "Select a row or Update list", "check", JOptionPane.WARNING_MESSAGE);
            } else {

                // --------
                idp = modeloTabla.getValueAt(vista.getTablaProductos().getSelectedRow(), 0).toString();
                String calculo = modeloTabla.getValueAt(vista.getTablaProductos().getSelectedRow(), 1).toString();
                String descripcion = modeloTabla.getValueAt(vista.getTablaProductos().getSelectedRow(), 2).toString();
                String fecha = modeloTabla.getValueAt(vista.getTablaProductos().getSelectedRow(), 3).toString();
                String cantidad = modeloTabla.getValueAt(vista.getTablaProductos().getSelectedRow(), 4).toString();
                String peso = modeloTabla.getValueAt(vista.getTablaProductos().getSelectedRow(), 5).toString();
                String tipo = modeloTabla.getValueAt(vista.getTablaProductos().getSelectedRow(), 6).toString();
                String precio = modeloTabla.getValueAt(vista.getTablaProductos().getSelectedRow(), 7).toString();
                // ---------------------
                vista.getTxtID().setText(idp);
                vista.getTxtCalculo().setText(calculo);
                vista.getTxtDescripcio().setText(descripcion);
                vista.getTxtFecha().setText(fecha);
                vista.getTxtCantidad().setText(cantidad);
                vista.getTxtPeso().setText(peso);
                vista.getTxtTipo().setText(tipo);
                vista.getTxtPrecio().setText(precio);
                
                //-----------------------------
                vista.getDlgProducto().setVisible(true);
                vista.getDlgProducto().setLocationRelativeTo(null);
                vista.getDlgProducto().setTitle("Edit Product");
                vista.getBtnGuardar().setText("Update");
                vista.getDlgProducto().setSize(446, 467);
            }
        }
    }

    //Registrar el prodcuto metodos
    public void nuevoProducto() {
        if (vista.getTxtCantidad().getText().equals("") || vista.getTxtDescripcio().getText().equals("") || vista.getTxtFecha().getText().equals("")
                || vista.getTxtCalculo().getText().equals("") || vista.getTxtPeso().getText().equals("") || vista.getTxtTipo().getText().equals("") || vista.getTxtPrecio().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Empty data please enter");
        } else {
            try {
                modelo.setId_producto(Integer.parseInt(vista.getTxtID().getText()));
                modelo.setCalculo_utilidad(Integer.parseInt(vista.getTxtCalculo().getText()));
                modelo.setDescripcion(vista.getTxtDescripcio().getText());
                modelo.setFecha(vista.getTxtFecha().getText());
                modelo.setCantidad(Integer.parseInt(vista.getTxtCantidad().getText()));
                modelo.setPeso_metal(Double.parseDouble(vista.getTxtPeso().getText()));
                modelo.setTipo_metal(vista.getTxtTipo().getText());
                modelo.setPrecio_unitario(Double.parseDouble(vista.getTxtPrecio().getText()));
                if (vista.getBtnGuardar().getText().contentEquals("Guardar")) {
                    modelo.insertarProducto();
                    JOptionPane.showMessageDialog(null, "Added successfully");
                    limpiarCampos();
                    cargarLista();
                } else {
                    modelo.actualizarProducto();
                    JOptionPane.showMessageDialog(null, "Successfully");
                    limpiarCampos();
                    cargarLista();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data entry error");
            }
        }
    }
    
    public int idProducto() {
        int id = modelo.id_autopro();
        return id;
    }
    
    public void limpiarCampos() {
        vista.getTxtCalculo().setText("");
        vista.getTxtDescripcio().setText("");
        vista.getTxtFecha().setText("");
        vista.getTxtCantidad().setText("");
        vista.getTxtPeso().setText("");
        vista.getTxtTipo().setText("");
        vista.getTxtPrecio().setText("");
        vista.getDlgProducto().setVisible(false);
    }
    
    public void validarCampos() {
        vista.getTxtCalculo().addKeyListener(validarNumeros(vista.getTxtCalculo()));
        vista.getTxtDescripcio().addKeyListener(validarLetras(vista.getTxtDescripcio()));
        vista.getTxtTipo().addKeyListener(validarLetras(vista.getTxtTipo()));
    }
    
    public void placeHolderFecha() {
        PlaceHolder txtbuscar = new PlaceHolder("dd/mm/AA", vista.getTxtFecha());
    }
}
