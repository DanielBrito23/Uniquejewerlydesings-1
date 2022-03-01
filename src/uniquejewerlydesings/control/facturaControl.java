/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.text.Document;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import uniquejewerlydesings.DBmodelo.cuerpoFacturaDB;

/**
 *
 * @author LENOVO
 */
public class facturaControl extends validacion {
//*** conxion 

    private Conexion conecta = new Conexion();
    Connection cn = conecta.conectarBD();

    // ** vista
    private Factura vistaFactura;
//    private PersonaIngreso vistaPersona;

    //**modelos base de datos
    private facturaDB factura;
    private productoDB modelo;
    private personaDB personaDB;
    private clienteDB clienteDB;
    private cuerpoFacturaDB cuerpoDB;

    //** tabla para los datos
    DefaultTableModel modeloTab;

    //***validaciones
    private validacion b;

    public facturaControl(Factura vistaFactura, facturaDB factura, productoDB modelo, personaDB personaDB, clienteDB clienteDB, cuerpoFacturaDB cuerpoDB) {
        this.vistaFactura = vistaFactura;
        this.factura = factura;
        this.modelo = modelo;
        this.personaDB = personaDB;
        this.clienteDB = clienteDB;
        this.cuerpoDB = cuerpoDB;
    }

    public void iniciarControl() {

        vistaFactura.getBtnNewUser().addActionListener(e -> formularioPersona());
        vistaFactura.getBtnbuscar().addActionListener(e -> buscar());
        vistaFactura.getBtnselecionado().addActionListener(e -> seleccion());

        //mostrar dialogo de producto y persona ingresp
        vistaFactura.getBuscarProdcuto().addActionListener(e -> listaProductoDialogo());
        vistaFactura.getBtnGuardar().addActionListener(e -> ingresoPersonaDialogo());
        vistaFactura.getBtnimprimir().addActionListener(e -> datosImprimir());
//        vistaFactura.getBtnimprimir().addActionListener(e -> reporte());

        validarCampos();
        cargarLista();
        ventana();

    }

    public void ventana() {
        vistaFactura.setVisible(true);
        vistaFactura.setLocationRelativeTo(null);
        vistaFactura.setTitle("Invoice");
        vistaFactura.getTxtIdCliente().setText(String.valueOf(IdCli()));
        vistaFactura.getTxtcuerpo().setText(String.valueOf(IdCuerpo()));
        vistaFactura.getTxtidfac().setVisible(false);
        vistaFactura.getTxtcuerpo().setVisible(false);
        vistaFactura.getTxtIdCliente().setVisible(false);
        incrementarId();

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

    public int IdFac() {
        int id = factura.id_autofactur();
        return id;
    }

    public int IdCuerpo() {
        int id = cuerpoDB.id_autoCuerpo();
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
            String cantidad, descripcion, precio, total, id;
            double x = 0.0;
            int canti = 0;

            if (filaSleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Select a row", "check", JOptionPane.WARNING_MESSAGE);
            } else {
                modeloTab = (DefaultTableModel) vistaFactura.getTablaProductos().getModel();
                descripcion = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 2).toString();
                id = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 0).toString();
                precio = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 7).toString();
                cantidad = vistaFactura.getTxtcantidad().getText();
                //borra la cantidad del textfield
                vistaFactura.getTxtcantidad().setText("");
                // metodos para calcular el precio 
                x = (Double.parseDouble(precio) * Integer.parseInt(cantidad));
                total = String.valueOf(x);

                //muestra los datos en la tabla 
                modeloTab = (DefaultTableModel) vistaFactura.getTablaFactura().getModel();
                String filaSeleelemto[] = {id, descripcion, cantidad, precio, total};
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

    public void reporte() {
        JasperReport reporte;
        String path = "/factura/factura.jasper";

//        String path = "F:\\ARCHIVOS\\programacion Visual\\CrudMvc1\\src\\Reporte\\Ejemplo_Reporte.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(path)); //Cargo el reporte al objeto
            Map<String, Object> params = new HashMap<String, Object>();
//            String aguja = vistaFactura.getTxtparametro().getText();
            params.put("cedula", vistaFactura.getTxtcedula());
            JasperPrint jprint = JasperFillManager.fillReport(reporte, params, cn); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint, false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte

            //mapaa de parametros
        } catch (JRException ex) {
            Logger.getLogger(UniqueJewerlyDesings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ingresoCliente() {
        //ingerso cliente
        clienteDB.setId_persona(Integer.parseInt(vistaFactura.getTxtid().getText()));
        clienteDB.setId_cliente(Integer.parseInt(vistaFactura.getTxtIdCliente().getText()));
        if (clienteDB.insertarCliente()) {
            JOptionPane.showMessageDialog(null, "Added successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Data entry error");
        }
        //ingreso encabezado
        factura.setId_encabezado(Integer.parseInt(vistaFactura.getTxtidfac().getText()));
        factura.setId_cliente(Integer.parseInt(vistaFactura.getTxtIdCliente().getText()));
        if (factura.insertarFactura()) {
            // JOptionPane.showMessageDialog(null, "Added successfully");
        }
        //ingreso cuerpo
//        cuerpoDB.setId_cuerpo(Integer.parseInt(vistaFactura.getTxtcuerpo().getText()));
//        cuerpoDB.setId_encabezado(Integer.parseInt(vistaFactura.getTxtidfac().getText()));
////        modelo.setId_encabezado(Integer.parseInt(vistaFactura.getTxtidfac().getText()));
//        cuerpoDB.setReparacion(vistaFactura.getTxtreparaciones().getText());
//        if (cuerpoDB.insertarCuerpo()) {
//            // JOptionPane.showMessageDialog(null, "Added successfully");
//        }
    }

    public void incrementarId() {
        vistaFactura.getTxtidfac().setText(String.valueOf(IdFac()));

    }

    // metodo imprimir datos test
    public void datosImprimir() {
        com.itextpdf.text.Document documento = new com.itextpdf.text.Document();

        try {

            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Downloads/" + vistaFactura.getTxtnombres().getText() + ".pdf"));

            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/images/logo.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            //datos para el cliente
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Información del cliente. \n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tablaCliente = new PdfPTable(5);
            tablaCliente.addCell("Cedula");
            tablaCliente.addCell("Nombre");
            tablaCliente.addCell("email");
            tablaCliente.addCell("Télefono");
            tablaCliente.addCell("Dirección");

            try {
                PreparedStatement pst = cn.prepareStatement(
                        "select * from persona where cedula = '" + vistaFactura.getTxtcedula().getText() + "'");
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {
                        tablaCliente.addCell(rs.getString(1));
                        tablaCliente.addCell(rs.getString(2));
                        tablaCliente.addCell(rs.getString(3));
                        tablaCliente.addCell(rs.getString(4));
                        tablaCliente.addCell(rs.getString(5));

                    } while (rs.next());

                    documento.add(tablaCliente);
                }

            } catch (Exception e) {
                System.out.println("Error de los datos de la persona");
            }

            documento.close();
             ingresoCliente();
            JOptionPane.showMessageDialog(null, "Reporte creado correctamente.");
           
        } catch (Exception e) {

            System.err.println("Error en PDF o ruta de imagen" + e);
            JOptionPane.showMessageDialog(null, "Error al generar PDF, contacte al administrador");
        }
    }

}
