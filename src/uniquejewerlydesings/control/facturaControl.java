/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import uniquejewerlydesings.DBmodelo.clienteDB;
import uniquejewerlydesings.DBmodelo.facturaDB;
import uniquejewerlydesings.DBmodelo.personaDB;
import uniquejewerlydesings.DBmodelo.productoDB;
import uniquejewerlydesings.UniqueJewerlyDesings;
import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.producto;
import uniquejewerlydesings.modelo.validacion;
import uniquejewerlydesings.vista.Factura;
import uniquejewerlydesings.vista.ListaProductos;
import uniquejewerlydesings.vista.PersonaIngreso;

/**
 *
 * @author LENOVO
 */
public class facturaControl extends validacion {
//*** conxion 

    private Conexion conecta = new Conexion();

    // ** vista
    private Factura vistaFactura;
//    private PersonaIngreso vistaPersona;

    //**modelos base de datos
    private facturaDB factura;
    private productoDB modelo;
    private personaDB personaDB;
    private clienteDB clienteDB;

    //** tabla para los datos
    DefaultTableModel modeloTab;

    //***validaciones
    private validacion b;

    public facturaControl(Factura vistaFactura, facturaDB factura, productoDB modelo, personaDB personaDB, clienteDB clienteDB) {
        this.vistaFactura = vistaFactura;
        this.factura = factura;
        this.modelo = modelo;
        this.personaDB = personaDB;
        this.clienteDB = clienteDB;
    }

    public void iniciarControl() {

        vistaFactura.getBtnNewUser().addActionListener(e -> formularioPersona());
        vistaFactura.getBtnbuscar().addActionListener(e -> buscar());
        vistaFactura.getBtnselecionado().addActionListener(e -> seleccion());

        //mostrar dialogo de producto y persona ingresp
        vistaFactura.getBuscarProdcuto().addActionListener(e -> listaProductoDialogo());
        vistaFactura.getBtnGuardar().addActionListener(e -> ingresoPersonaDialogo());
        vistaFactura.getBtnimprimir().addActionListener(e -> ingresoCliente());
        //vistaFactura.getBtnimprimir().addActionListener(e -> imprimir());

        validarCampos();
        cargarLista();
        ventana();

    }

    public void ventana() {
        vistaFactura.setVisible(true);
        vistaFactura.setLocationRelativeTo(null);
        vistaFactura.setTitle("Invoice");
        vistaFactura.getTxtIdCliente().setText(String.valueOf(IdCli()));

    }

    public void formularioPersona() {
        vistaFactura.getDialogoPersona().setVisible(true);
        vistaFactura.getDialogoPersona().setLocationRelativeTo(null);
        vistaFactura.getDialogoPersona().setTitle("New Person");
        vistaFactura.getDialogoPersona().setSize(349, 349);
        vistaFactura.getTxtID().setText(String.valueOf(idper()));

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

    public int IdCli() {
        int id = clienteDB.id_autoCli();
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
                cantidad = vistaFactura.getTxtcantidad().getText();
                //borra la cantidad del textfield
                vistaFactura.getTxtcantidad().setText("");
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

    public void ingresoPersonaDialogo() {

        if (vistaFactura.getTxtCedula().getText().equals("") || vistaFactura.getTxtNombres().getText().equals("") || vistaFactura.getTxtCorreo().getText().equals("")
                || vistaFactura.getTxtTelefono().getText().equals("") || vistaFactura.getTxtCorreo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Empty data please enter");
        } else {
            personaDB.setId_persona(Integer.parseInt(vistaFactura.getTxtID().getText()));
            personaDB.setCedula(vistaFactura.getTxtCedula().getText());
            personaDB.setNombres(vistaFactura.getTxtNombres().getText());
            personaDB.setDireccion(vistaFactura.getTxtDireccion().getText());
            personaDB.setTelefono(vistaFactura.getTxtTelefono().getText());
            personaDB.setCorreo(vistaFactura.getTxtCorreo().getText());
            if (personaDB.insertarPersona()) {
                JOptionPane.showMessageDialog(null, "Added successfully");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Data entry error");
            }
        }
        vistaFactura.getTxtid().setText(personaDB.getId_persona() + "");
        vistaFactura.getTxtcedula().setText(personaDB.getCedula() + "");
        vistaFactura.getTxtnombres().setText(personaDB.getNombres() + "");
        vistaFactura.getTxtdireccion().setText(personaDB.getDireccion() + "");
        vistaFactura.getTxttelefono().setText(personaDB.getTelefono() + "");
        vistaFactura.getTxtcorreo().setText(personaDB.getCorreo() + "");
    }

    public void limparCampos() {
        vistaFactura.getTxtCedula().setText("");
        vistaFactura.getTxtNombres().setText("");
        vistaFactura.getTxtCorreo().setText("");
        vistaFactura.getTxtTelefono().setText("");
        vistaFactura.getTxtDireccion().setText("");
    }

    public void validarCampos() {
        vistaFactura.getTxtNombres().addKeyListener(validarLetras(vistaFactura.getTxtNombres()));
        vistaFactura.getTxtDireccion().addKeyListener(validarLetras(vistaFactura.getTxtDireccion()));
        vistaFactura.getTxtTelefono().addKeyListener(validarCelular(vistaFactura.getTxtTelefono()));
        vistaFactura.getTxtNombres().addKeyListener(validarLetras(vistaFactura.getTxtNombres()));
    }

    public void imprimir() {

//        try {
//            String ruta="scr/uniuniquejewerlydesings/factura/factura.jrxml";
//            Map parametros=new HashMap();
//            parametros.put("cedula",vistaFactura.getTxtcedula().getText());
//            JasperPrint informe=JasperFillManager.fillReport(ruta, parametros,conecta.conectarBD());
//            JasperViewer ventanavisor=new JasperViewer(informe,false);
//            ventanavisor.setTitle("FACTURA");
//            ventanavisor.setVisible(true);
//            
//        } catch (HeadlessException| JRException e) {
//            JOptionPane.showConfirmDialog(null,"Error en el reporte","error",JOptionPane.ERROR_MESSAGE);
//        }
        try {
            JasperReport jr = JasperCompileManager.compileReport("src/uniuniquejewerlydesings/factura/factura.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conecta.conectarBD());
            JasperViewer ventanavisor = new JasperViewer(jp);
            ventanavisor.setVisible(true);
        } catch (JRException e) {
        }
    }

    public void ingresoCliente() {
        clienteDB.setId_persona(Integer.parseInt(vistaFactura.getTxtid().getText()));
        clienteDB.setId_cliente(Integer.parseInt(vistaFactura.getTxtIdCliente().getText()));
        if (clienteDB.insertarCliente()) {
            JOptionPane.showMessageDialog(null, "Added successfully");
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Data entry error");
        }
    }

}
