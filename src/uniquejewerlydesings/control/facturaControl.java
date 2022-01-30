/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.DBmodelo.facturaDB;
import uniquejewerlydesings.DBmodelo.personaDB;
import uniquejewerlydesings.DBmodelo.productoDB;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.producto;
import uniquejewerlydesings.vista.Factura;
import uniquejewerlydesings.vista.ListaProductos;
import uniquejewerlydesings.vista.PersonaIngreso;

/**
 *
 * @author LENOVO
 */
public class facturaControl {

    // ** vista
    private Factura vistaFactura;
    private PersonaIngreso vistaPersona;

    //**modelos base de datos
    private facturaDB factura;
    private productoDB modelo;
    private personaDB personaDB;

    //** tabla para los datos
    DefaultTableModel modeloTab;

    public facturaControl(Factura vistaFactura, PersonaIngreso vistaPersona, facturaDB factura, productoDB modelo, personaDB personaDB) {
        this.vistaFactura = vistaFactura;
        this.vistaPersona = vistaPersona;
        this.factura = factura;
        this.modelo = modelo;
        this.personaDB = personaDB;
    }

    public void iniciarControl() {
        vistaFactura.getBtnNewUser().addActionListener(e -> formularioPersona());
        vistaFactura.getBtnbuscar().addActionListener(e -> buscar());
        vistaFactura.getBuscarProdcuto().addActionListener(e -> listaProductoDialogo());
        vistaFactura.getBtnselecionado().addActionListener(e -> seleccion());
//        vistaFactura.getBtnactualizar().addActionListener(e -> actualizarDatos());

        cargarLista();
        ventana();

    }

    public void ventana() {
        vistaFactura.setVisible(true);
        vistaFactura.setLocationRelativeTo(null);
        vistaFactura.setTitle("Invoice");
    }

    public void formularioPersona() {
        vistaPersona.setVisible(true);
        vistaPersona.setLocationRelativeTo(null);
        vistaPersona.setTitle("New Customer");
        vistaPersona.getTxtID().setText(String.valueOf(idper()));

    }

    public void listaProductoDialogo() {
        vistaFactura.getDialiogproducto().setVisible(true);
        vistaFactura.getDialiogproducto().setLocationRelativeTo(null);
        vistaFactura.getDialiogproducto().setTitle("Product List");
        vistaFactura.getDialiogproducto().setSize(755, 302);
    }

    public int idper() {
        int id = personaDB.id_autoper();
        return id;
    }

    private void cargarLista() {
        int canFilas = vistaFactura.getTablaProductos().getRowCount();
        for (int i = canFilas - 1; i >= 0; i--) {
            modeloTab.removeRow(i);
        }
        modeloTab = (DefaultTableModel) vistaFactura.getTablaProductos().getModel();
        List<producto> lista;
        //  modelo.setIdpersona(vista.getTxtBuscar().getText());
        try {
            lista = modelo.listaProductos();
            int columnas = modeloTab.getColumnCount();
            for (int i = 0; i < lista.size(); i++) {
                modeloTab.addRow(new Object[columnas]);
                vistaFactura.getTablaProductos().setValueAt(lista.get(i).getId_producto(), i, 0);
                vistaFactura.getTablaProductos().setValueAt(lista.get(i).getCalculo_utilidad(), i, 1);
                vistaFactura.getTablaProductos().setValueAt(lista.get(i).getDescripcion(), i, 2);
                vistaFactura.getTablaProductos().setValueAt(lista.get(i).getFecha(), i, 3);
                vistaFactura.getTablaProductos().setValueAt(lista.get(i).getCantidad(), i, 4);
                vistaFactura.getTablaProductos().setValueAt(lista.get(i).getPeso_metal(), i, 5);
                vistaFactura.getTablaProductos().setValueAt(lista.get(i).getTipo_metal(), i, 6);
                vistaFactura.getTablaProductos().setValueAt(lista.get(i).getPrecio_unitario(), i, 7);
            }

        } catch (Exception ex) {
            System.out.println("Error en el listar control: " + ex.getMessage());
        }
    }

    public void buscar() {
        if (vistaFactura.getTxtcedula().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data not found");
        } else {
            persona p = factura.buscarPersonaId(vistaFactura.getTxtcedula().getText());
            vistaFactura.getTxtid().setText(p.getId_persona() + "");
            vistaFactura.getTxtnombres().setText(p.getNombres() + "");
            vistaFactura.getTxtdireccion().setText(p.getDireccion() + "");
            vistaFactura.getTxttelefono().setText(p.getTelefono() + "");
            vistaFactura.getTxtcorreo().setText(p.getCorreo() + "");
        }
    }
// metodo para pasar los datos de una tabla a otra

    public void seleccion() {
        int filaSleccionada = vistaFactura.getTablaProductos().getSelectedRow();
        try {
            String cantidad, descripcion, precio, total;
            double x = 0.0;
            int canti = 0;

            if (filaSleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Select a row", "check", JOptionPane.WARNING_MESSAGE);
            } else {
                modeloTab = (DefaultTableModel) vistaFactura.getTablaProductos().getModel();
                descripcion = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 2).toString();
//                cantidad = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 4).toString();
                precio = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 7).toString();
                cantidad=vistaFactura.getTxtcantidad().getText();
                // metodos para calcular el precio 
                x = (Double.parseDouble(precio) * Integer.parseInt(cantidad));
                total = String.valueOf(x);

                //muestra los datos en la tabla 
                modeloTab = (DefaultTableModel) vistaFactura.getTablaFactura().getModel();
                String filaSeleelemto[] = {descripcion, cantidad, precio, total};
                modeloTab.addRow(filaSeleelemto);
            }
        } catch (Exception e) {

        }

    }

    public void actualizarDatos() {
       modelo.actualizarProducto();
    }
}
