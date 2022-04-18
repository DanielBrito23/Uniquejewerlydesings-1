/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.lt;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.DBmodelo.cuerpoFacturaDB;
import javax.swing.table.TableColumnModel;
import uniquejewerlydesings.DBmodelo.facturaDB;
import uniquejewerlydesings.DBmodelo.personaDB;
import uniquejewerlydesings.DBmodelo.productoDB;
import uniquejewerlydesings.modelo.cuerpoFactura;
import uniquejewerlydesings.modelo.encabezadoFactura;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.producto;
import uniquejewerlydesings.vista.Factura;
import uniquejewerlydesings.vista.ListaFactura;

/**
 *
 * @author corin
 */
public class listaFacturaControl {

    private cuerpoFacturaDB cuerpoBD;
    private TableColumnModel columnModel;
    private ListaFactura vistaLista;
    private Factura vistaFactura;
    private facturaControl facContrl;
    DefaultTableModel modeloTabDlReg = null;
    DefaultTableModel modeloTab;
//    public static int enviar = 0;
//    int num = 0;

    public listaFacturaControl(cuerpoFacturaDB cuerpoBD, ListaFactura vistaLista, Factura vistaFactura, facturaControl facContrl) {
        this.cuerpoBD = cuerpoBD;
        this.vistaLista = vistaLista;
        this.vistaFactura = vistaFactura;
        this.facContrl = facContrl;
    }

    public void inciaControl() {
        vistaLista.setVisible(true);
        vistaLista.setLocationRelativeTo(null);
        vistaLista.setTitle("List Invoice");
        cuerpoBD = new cuerpoFacturaDB();
        columnModel = vistaLista.getJtblista().getColumnModel();
        listar();
        popTableMenu();
        popTableFactura();

        cargarLista();

        vistaFactura.getBtnbuscar().addActionListener(e -> editDatosFactura());
        vistaFactura.getBuscarProdcuto().addActionListener(e -> listaProductoDialogo());
        vistaFactura.getBtnselecionado().addActionListener(e -> seleccion());
    }

    public void ocultarColumnasTabla(JTable tbl, int columna[]) {
        for (int i = 0; i < columna.length; i++) {
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }

    private void listar() {
        vistaLista.getJtblista().setModel(cuerpoBD.getDatosInventario());
        //ocultarColumnasTabla(vistaLista.getJtblista(), new int[] {0, 1});
    }

    public void popTableMenu() {
        JPopupMenu pM = new JPopupMenu();
        JMenuItem itEditar = new JMenuItem("EDITAR");
        JMenuItem itEliminar = new JMenuItem("BORRAR");
        itEditar.addActionListener((ActionEvent e) -> {
            cargarFactura();
        });
        itEliminar.addActionListener((ActionEvent e) -> {

        });
        pM.add(itEditar);
        pM.add(itEliminar);
        vistaLista.getJtblista().setComponentPopupMenu(pM);
    }

    public void popTableFactura() {
        JPopupMenu pM = new JPopupMenu();
        JMenuItem quitar = new JMenuItem("Remove product");
        quitar.addActionListener((ActionEvent e) -> {
            quitarProducto();
        });
        pM.add(quitar);
        vistaFactura.getTablaFactura().setComponentPopupMenu(pM);
    }

    public void quitarProducto() {
        facturaDB facturaDB = new facturaDB();
        productoDB prodDB = new productoDB();
        int fsel = vistaFactura.getTablaFactura().getSelectedRow();
        if (fsel == -1) {
            JOptionPane.showMessageDialog(null, "Select a row", "Verificación", JOptionPane.WARNING_MESSAGE);
        } else {
            int desicion = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this product?");
            if (desicion == JOptionPane.YES_OPTION) {
                modeloTab = (DefaultTableModel) vistaFactura.getTablaFactura().getModel();
                String cod = modeloTab.getValueAt(vistaFactura.getTablaFactura().getSelectedRow(), 0).toString();
                prodDB.setId_producto(Integer.parseInt(cod));
                if (facturaDB.QuitarProducto(Integer.parseInt(cod), Integer.parseInt(vistaFactura.getTxtidfac().getText()))) {
                    JOptionPane.showMessageDialog(null, "Dato borrado correctamente");
                    listar();
                } else {
                    JOptionPane.showMessageDialog(null, "Dato no borrado");
                }
            }
        }

    }

    public void limpiarAllFactura() {
        vistaFactura.getTxtFecha().setText("");
        vistaFactura.getTxtcedula().setText("");
        vistaFactura.getTxtnombres().setText("");
        vistaFactura.getTxtdireccion().setText("");
        vistaFactura.getTxttelefono().setText("");
        vistaFactura.getTxtcorreo().setText("");

        vistaFactura.getTxtreparaciones().setText("");
        vistaFactura.getTxtReparacion().setText("");
        vistaFactura.getTxtpricetotal().setText(String.valueOf(""));
        vistaFactura.getTxtAbono().setText(String.valueOf(""));
        vistaFactura.getTxtValorPediente().setText(String.valueOf(""));

        int canFilas = vistaFactura.getTablaFactura().getRowCount();
        for (int i = canFilas - 1; i >= 0; i--) {
            modeloTabDlReg.removeRow(i);
        }

    }

    public void cargarFactura() {

        facturaDB facturaDB = new facturaDB();
        DefaultTableModel modelTabla = (DefaultTableModel) vistaLista.getJtblista().getModel();
        int fsel = vistaLista.getJtblista().getSelectedRow();
        if (fsel == -1) {
            JOptionPane.showMessageDialog(null, "select a row", "Verificación", JOptionPane.WARNING_MESSAGE);
        } else {
            limpiarAllFactura();
            String fecha = modelTabla.getValueAt(vistaLista.getJtblista().getSelectedRow(), 0).toString();
            String ced = modelTabla.getValueAt(vistaLista.getJtblista().getSelectedRow(), 1).toString();
            String nomApe = modelTabla.getValueAt(vistaLista.getJtblista().getSelectedRow(), 2).toString();
            String idFactura = modelTabla.getValueAt(vistaLista.getJtblista().getSelectedRow(), 3).toString();
            String direccion = modelTabla.getValueAt(vistaLista.getJtblista().getSelectedRow(), 4).toString();
            String telf = modelTabla.getValueAt(vistaLista.getJtblista().getSelectedRow(), 5).toString();
            String correo = modelTabla.getValueAt(vistaLista.getJtblista().getSelectedRow(), 6).toString();

            vistaFactura.getTxtFecha().setText(fecha);
            vistaFactura.getTxtcedula().setText(ced);
            vistaFactura.getTxtnombres().setText(nomApe);
            vistaFactura.getTxtdireccion().setText(direccion);
            vistaFactura.getTxttelefono().setText(telf);
            vistaFactura.getTxtcorreo().setText(correo);
            vistaFactura.getTxtidfac().setText(idFactura);

            ArrayList<Object> listObjects = facturaDB.buscarFactura(ced, idFactura);
            persona per = (persona) listObjects.get(0);
            encabezadoFactura ef = (encabezadoFactura) listObjects.get(1);
            cuerpoFactura cf = (cuerpoFactura) listObjects.get(2);
            producto pro = (producto) listObjects.get(3);

            vistaFactura.getTxtreparaciones().setText(cf.getReparacion());
            vistaFactura.getTxtReparacion().setText(cf.getReparacion());
            vistaFactura.getTxtpricetotal().setText(String.valueOf(cf.getTotal_reparacion()));
            vistaFactura.getTxtAbono().setText(String.valueOf(cf.getAbono()));
            vistaFactura.getTxtValorPediente().setText(String.valueOf(cf.getValor_pendiente()));
            vistaFactura.getTxtcuerpo().setText(String.valueOf(facContrl.IdCuerpo()));
            modeloTabDlReg = (DefaultTableModel) vistaFactura.getTablaFactura().getModel();
            
            listarTabFact(ced, idFactura); 

            vistaFactura.getTxtcedula().setEnabled(false);
            vistaFactura.getBtnbuscar().setText("Editar");
            vistaFactura.getBtnNewUser().setVisible(false);
            vistaFactura.getBtnimprimir().setText("Reprint");
            vistaFactura.setVisible(true);
        }
    }
    
    public void listarTabFact(String cedula, String idEncabezado){
        facturaDB facturaDB = new facturaDB();
         List<producto> lista;
            try {
                lista = facturaDB.llenarTablaFactura(cedula, idEncabezado);
                int columnas = modeloTabDlReg.getColumnCount();
                for (int i = 0; i < lista.size(); i++) {
                    modeloTabDlReg.addRow(new Object[columnas]);
                    vistaFactura.getTablaFactura().setValueAt(lista.get(i).getId_producto(), i, 0);
                    vistaFactura.getTablaFactura().setValueAt(lista.get(i).getDescripcion(), i, 1);
                    vistaFactura.getTablaFactura().setValueAt(lista.get(i).getCantidad(), i, 2);
                    vistaFactura.getTablaFactura().setValueAt(lista.get(i).getPrecio_unitario(), i, 3);
                }

            } catch (Exception ex) {
                ex.getMessage();
            }
    }
    
    public void listaProductoDialogo() {
        facContrl.listaProductoDialogo();
    }

    public void editDatosFactura() {
        personaDB pDB = new personaDB();
        facturaDB facturaDB = new facturaDB();
        pDB.setCedula(vistaFactura.getTxtcedula().getText());
        pDB.setNombres(vistaFactura.getTxtnombres().getText());
        pDB.setDireccion(vistaFactura.getTxtdireccion().getText());
        pDB.setCorreo(vistaFactura.getTxtcorreo().getText());
        pDB.setTelefono(vistaFactura.getTxttelefono().getText());

        if (facturaDB.editarPersonas(vistaFactura.getTxtcedula().getText(), pDB)) {
            facContrl.insertarDetallesFactura();
            JOptionPane.showMessageDialog(null, "Successfully Edited");
            vistaFactura.setVisible(false);
            listar();
        }
    }

    private void cargarLista() {
        facContrl.cargarLista();
    }

    public void seleccion() {
        facContrl.seleccion();
    }

}
