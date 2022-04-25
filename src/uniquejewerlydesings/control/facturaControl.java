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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.DBmodelo.clienteDB;
import uniquejewerlydesings.DBmodelo.facturaDB;
import uniquejewerlydesings.DBmodelo.personaDB;
import uniquejewerlydesings.DBmodelo.productoDB;
import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.producto;
import uniquejewerlydesings.modelo.validacion;
import uniquejewerlydesings.vista.Factura;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTextField;

import uniquejewerlydesings.DBmodelo.cuerpoFacturaDB;

/**
 *
 * @author LENOVO
 */
public class facturaControl extends validacion {

    // variables para el calculo
    double precioR;
    static double total;
    static double totalp;
    static double totalabono;

    double abono;
    double valor_pendiente;
    public static String id_producto;

    String article;
    String cuantity;
    String price;
    String totalTabla;

    String[] producto2 = new String[4];
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
        vistaFactura.getBtnClear().addActionListener(e -> limpiarCamposInvoice());
        vistaFactura.getTxtcantidad().addKeyListener(validarNumeros(vistaFactura.getTxtcantidad()));
//        vistaFactura.getBtnimprimir().addActionListener(e -> reporte());
        vistaFactura.setTitle("Invoice");
        vistaFactura.getTxtidfac().setVisible(false);
         vistaFactura.getTxtIdCliente().setText(String.valueOf(IdCli()));
        vistaFactura.getTxtcuerpo().setText(String.valueOf(IdCuerpo()));
        vistaFactura.getTxtcuerpo().setVisible(false);
        vistaFactura.getTxtIdCliente().setVisible(false);

        //para poner la fecha
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        System.out.println("yyyy/MM/dd hh:mm:ss-> "+fecha.format(LocalDateTime.now()));
        vistaFactura.getTxtFecha().setText("" + fecha.format(LocalDateTime.now()));
        //
        incrementarId();
        validarCampos();
        cargarLista();
        //ventana();
        inhabilitarCamposInvoice();

    }

    public void ventana() {
        vistaFactura.setVisible(true);
        vistaFactura.setLocationRelativeTo(null);
        vistaFactura.setTitle("Invoice");
        vistaFactura.getTxtIdCliente().setText(String.valueOf(IdCli()));
        vistaFactura.getTxtcuerpo().setText(String.valueOf(IdCuerpo()));
        vistaFactura.getTxtidfac().setVisible(true);
        vistaFactura.getTxtcuerpo().setVisible(false);
        vistaFactura.getTxtIdCliente().setVisible(true);

        //para poner la fecha
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        System.out.println("yyyy/MM/dd hh:mm:ss-> "+fecha.format(LocalDateTime.now()));
        vistaFactura.getTxtFecha().setText("" + fecha.format(LocalDateTime.now()));
        //
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
        if (vistaFactura.getTxtcedula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty identification", "Enter values valid", JOptionPane.WARNING_MESSAGE);
        } else {
            vistaFactura.getDialiogproducto().setVisible(true);
            vistaFactura.getDialiogproducto().setLocationRelativeTo(null);
            vistaFactura.getDialiogproducto().setTitle("Product List");
            vistaFactura.getDialiogproducto().setSize(755, 302);
        }
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

    public void limpiarTabla() {
        int canFilas = vistaFactura.getTablaProductos().getRowCount() - 1;
        modeloTab.setRowCount(0);
        System.out.println("llegoooo");
        for (int i = canFilas - 1; i >= 0; i--) {
            System.out.println("i: " + i + " Filas: " + canFilas);
            modeloTab.removeRow(i);
        }
    }

    public void cargarLista() {
        //limpiarTabla(canFilas);
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
                System.out.println("cargarLista");
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
    ArrayList<String> idsProd = new ArrayList<String>();

    public void seleccion() {
        int filaSleccionada = vistaFactura.getTablaProductos().getSelectedRow();
        try {
            String id, descripcion, precioUni, cantidad, importe;
            String cantProdTab;
            double x = 0.0, y = 0.0, calcula = 0.0;
            int canti = 0;

            if (filaSleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Select a row", "check", JOptionPane.WARNING_MESSAGE);
            } else {
                modeloTab = (DefaultTableModel) vistaFactura.getTablaProductos().getModel();

                // valores que tiene la tabla 
                id = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 0).toString();
                descripcion = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 2).toString();
                cantProdTab = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 4).toString();
                precioUni = vistaFactura.getTablaProductos().getValueAt(filaSleccionada, 7).toString();

                cantidad = vistaFactura.getTxtcantidad().getText();
                if (vistaFactura.getTxtcantidad().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter values", "check", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (verifCantProd(Integer.parseInt(cantidad), Integer.parseInt(id))) {

//                vistaFactura.getTxtcantidad().setText("");
                        // calculos .... 
                        x = (Double.parseDouble(precioUni) * Integer.parseInt(cantidad));
                        // total del precio uni * cantidad
                        importe = String.valueOf(x);

                        modeloTab = (DefaultTableModel) vistaFactura.getTablaFactura().getModel();
                        String filaElementos[] = {id, descripcion, cantidad, precioUni, importe};
                        modeloTab.addRow(filaElementos);
                        idsProd.add(id);
                        calcula = (Double.parseDouble(precioUni) * Integer.parseInt(vistaFactura.getTxtcantidad().getText()));
                        System.out.println("calcula.." + calcula);

                        System.out.println("total precio ..." + total);
                        vistaFactura.getTxtpricetotal().setText("" + total);

                        if (vistaFactura.getTxtAbono().getText().contains("") || vistaFactura.getTxtReparacion().getText().contains("")) {
                            total = total + calcula;
                            System.out.println("total precio sin abono y sin repara ..." + total);
                            vistaFactura.getTxtpricetotal().setText("" + total);

                            precioR = Double.parseDouble(vistaFactura.getTxtReparacion().getText());
                            totalp = total + precioR;
                            System.out.println("total + repa " + totalp);
                            vistaFactura.getTxtpricetotal().setText("" + totalp);

                            abono = Double.parseDouble(vistaFactura.getTxtAbono().getText());
//                    total = total - abono;
                            totalabono = totalp - abono;
                            vistaFactura.getTxtValorPediente().setText("" + totalabono);
//                    vistaFactura.getTxtValorPediente().setText("" + total);
                            System.out.println("total - abono" + totalabono);
                        } else {
//
//                    abono = Double.parseDouble(vistaFactura.getTxtAbono().getText());
//                    totalabono = total - abono;
//                    vistaFactura.getTxtTotal().setText("" + totalabono);
//
//                    System.out.println("total - abono" + totalabono);
//                    precioR = Double.parseDouble(vistaFactura.getTxtReparacion().getText());
                        }

                    }
                }

            }

        } catch (HeadlessException | NumberFormatException e) {
            e.getStackTrace();
        }
        cargarLista();
        System.out.println("cargarLista");
    }

    public boolean verifCantProd(int cantidad, int idPro) {
        System.out.println("cnatidad: " + cantidad + " cantProdTab:" + idPro);
        return modelo.restarCalProd(cantidad, idPro);
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

        for (int i = 0; i < vistaFactura.getTablaFactura().getRowCount(); i++) {
            id_producto = vistaFactura.getTablaFactura().getValueAt(i, 0).toString();
        }
        cuerpoDB.setId_cuerpo(IdCuerpo());
        cuerpoDB.setId_encabezado(Integer.parseInt(vistaFactura.getTxtidfac().getText()));
        cuerpoDB.setTotal_reparacion(Double.parseDouble(vistaFactura.getTxtReparacion().getText()));
//        cuerpoDB.setTotal_reparacion(100.0);
        cuerpoDB.setReparacion(vistaFactura.getTxtreparaciones().getText());
//        cuerpoDB.setReparacion("holaaaa");
        cuerpoDB.setTotal(Double.parseDouble(vistaFactura.getTxtpricetotal().getText()));
//        cuerpoDB.setTotal(100.50);
        cuerpoDB.setAbono(Double.parseDouble(vistaFactura.getTxtAbono().getText()));
//        cuerpoDB.setAbono(20);
        cuerpoDB.setValor_pendiente(Double.parseDouble(vistaFactura.getTxtValorPediente().getText()));
//        cuerpoDB.setValor_pendiente(90);
//        cuerpoDB.setProducto(vistaFactura.getTxtpricetotal().setText(id_producto));
        insertarDetallesFactura();

    }

    public void insertarDetallesFactura() {
        Iterator<String> iterator = idsProd.iterator();
        //int valor = IdCuerpo();
        System.out.println("iterator: " + iterator.toString());
        while (iterator.hasNext()) {
            String codProd = iterator.next();
            System.out.println("codProd: " + codProd);
            if (cuerpoDB.insertarCuerpo(codProd, IdCuerpo(), Integer.parseInt(vistaFactura.getTxtidfac().getText()))) {

                // JOptionPane.showMessageDialog(null, "Added successfully");
            }
        }
    }

    public void incrementarId() {
        vistaFactura.getTxtidfac().setText(String.valueOf(IdFac()));

    }

    // metodo imprimir datos test
    public void datosImprimir() {
        if (vistaFactura.getTxtcedula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter values in identification", "Empty fields", JOptionPane.WARNING_MESSAGE);
        } else {
            if (vistaFactura.getTxtid().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter values", "Empty fields", JOptionPane.WARNING_MESSAGE);
            } else {
//                if (vistaFactura.getTxtreparaciones().getText().isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Enter values in repation", "Empty fields", JOptionPane.WARNING_MESSAGE);
//                } else {
                com.itextpdf.text.Document documento = new com.itextpdf.text.Document();

                try {

                    String ruta = System.getProperty("user.home");
                    PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Downloads/" + vistaFactura.getTxtnombres().getText() + ".pdf"));

                    com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/images/aprobado.jpg");
                    header.scaleToFit(100, 100);
                    header.setAlignment(Chunk.ALIGN_LEFT);

                    //datos para el cliente
                    Paragraph parrafo = new Paragraph();
                    parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                    parrafo.add("Información del cliente. \n \n");
                    parrafo.setFont(FontFactory.getFont("Tahoma", 12, Font.BOLD, BaseColor.BLACK));

                    documento.open();
                    documento.add(header);
                    documento.add(parrafo);

                    documento.add(Chunk.NEWLINE);
                    Paragraph date = new Paragraph("DATE:");
                    date.add(vistaFactura.getTxtFecha().getText());
                    date.getFont().setStyle(Font.BOLD);
                    date.getFont().setSize(10);

                    Paragraph name = new Paragraph("CUSTOM NAME:");
                    name.add(vistaFactura.getTxtnombres().getText());
                    name.getFont().setStyle(Font.BOLD);
                    name.getFont().setSize(10);

                    Paragraph address = new Paragraph("ADDRESS:");
                    address.add(vistaFactura.getTxtnombres().getText());
                    address.getFont().setStyle(Font.BOLD);
                    address.getFont().setSize(10);

                    Paragraph phone = new Paragraph("ADDRESS:");
                    phone.add(vistaFactura.getTxtdireccion().getText());
                    phone.getFont().setStyle(Font.BOLD);
                    phone.getFont().setSize(10);

                    Paragraph email = new Paragraph("EMAIL:");
                    email.add(vistaFactura.getTxtcorreo().getText());
                    email.getFont().setStyle(Font.BOLD);
                    email.getFont().setSize(10);

//            PdfPTable tabla = new PdfPTable(1);
//            tabla.addCell(date);
                    documento.add(date);
                    documento.add(name);
                    documento.add(address);
                    documento.add(phone);
                    documento.add(email);
//            documento.add(tabla);

                    Paragraph columna1 = new Paragraph("ARTICLES");
                    columna1.getFont().setStyle(Font.BOLD);
                    columna1.getFont().setSize(10);

                    Paragraph columna2 = new Paragraph("QUANTITY");
                    columna2.getFont().setStyle(Font.BOLD);
                    columna2.getFont().setSize(10);

                    Paragraph columna3 = new Paragraph("UNIT PRICE");
                    columna3.getFont().setStyle(Font.BOLD);
                    columna3.getFont().setSize(10);

                    Paragraph columna4 = new Paragraph("TOTAL");
                    columna4.getFont().setStyle(Font.BOLD);
                    columna4.getFont().setSize(10);

                    PdfPTable tablaProducto = new PdfPTable(4);
                    tablaProducto.addCell(columna1);
                    tablaProducto.addCell(columna2);
                    tablaProducto.addCell(columna3);
                    tablaProducto.addCell(columna4);

                    documento.add(Chunk.NEWLINE);

                    for (int i = 0; i < vistaFactura.getTablaFactura().getRowCount(); i++) {

                        article = vistaFactura.getTablaFactura().getValueAt(i, 1).toString();
                        cuantity = vistaFactura.getTablaFactura().getValueAt(i, 2).toString();
                        price = vistaFactura.getTablaFactura().getValueAt(i, 3).toString();
                        totalTabla = vistaFactura.getTablaFactura().getValueAt(i, 4).toString();

                        tablaProducto.addCell(article);
                        tablaProducto.addCell(cuantity);
                        tablaProducto.addCell(price);
                        tablaProducto.addCell(totalTabla);

                    }
                    documento.add(tablaProducto);

                    documento.add(Chunk.NEWLINE);

                    Paragraph repair = new Paragraph("REPAIR:");
                    repair.add(vistaFactura.getTxtreparaciones().getText());
                    repair.getFont().setStyle(Font.BOLD);
                    repair.getFont().setSize(10);

                    PdfPTable tabla = new PdfPTable(1);
                    tabla.addCell(repair);
                    documento.add(tabla);

//          documento.add(new Paragraph("REPAIR:" + vistaFactura.getTxtreparaciones().getText()));
                    documento.add(Chunk.NEWLINE);
                    Paragraph total = new Paragraph("TOTAL:");
                    total.add(vistaFactura.getTxtpricetotal().getText());
                    total.getFont().setStyle(Font.BOLD);
                    total.getFont().setSize(10);

                    Paragraph advance = new Paragraph("ADVANCE:");
                    advance.add(vistaFactura.getTxtAbono().getText());
                    advance.getFont().setStyle(Font.BOLD);
                    advance.getFont().setSize(10);

                    Paragraph balance = new Paragraph("BALANCE:");
                    balance.add(vistaFactura.getTxtValorPediente().getText());
                    balance.getFont().setStyle(Font.BOLD);
                    balance.getFont().setSize(10);

                    documento.add(total);
                    documento.add(advance);
                    documento.add(balance);

                    documento.close();
                    ingresoCliente();
                    JOptionPane.showMessageDialog(null, "Invoice created successfully");

                } catch (Exception e) {
                    System.err.println("Error en PDF o ruta de imagen" + e);
                    JOptionPane.showMessageDialog(null, "Error al generar PDF, contacte al administrador");
                }
            }
        }

    }

    public void limpiarCamposInvoice() {
        if (vistaFactura.getTxtid().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter valid values ​​in identification", "Empty fields", JOptionPane.WARNING_MESSAGE);
        } else {
            vistaFactura.getTxtcedula().setText("");
            vistaFactura.getTxtid().setText("");
            vistaFactura.getTxtnombres().setText("");
            vistaFactura.getTxtdireccion().setText("");
            vistaFactura.getTxttelefono().setText("");
            vistaFactura.getTxtcorreo().setText("");
            vistaFactura.getTxtcedula().setText("");
            vistaFactura.getTxtidfac().setText("");
            vistaFactura.getTxtFecha().setText("");
            vistaFactura.getTxtReparacion().setText("");
            vistaFactura.getTxtreparaciones().setText("");
            vistaFactura.getTxtpricetotal().setText("");
            vistaFactura.getTxtAbono().setText("");
            vistaFactura.getTxtValorPediente().setText("");
            vistaFactura.getTxtcuerpo().setText("");
            vistaFactura.getTxtcuerpo().setText(String.valueOf(IdCuerpo()));
            vistaFactura.getTxtIdCliente().setText(String.valueOf(IdCli()));
            DefaultTableModel tblFact = (DefaultTableModel) vistaFactura.getTablaFactura().getModel();
            tblFact.setRowCount(0);
        }

    }

    public void inhabilitarCamposInvoice() {
        vistaFactura.getTxtnombres().setEnabled(false);
        vistaFactura.getTxtdireccion().setEnabled(false);
        vistaFactura.getTxttelefono().setEnabled(false);
        vistaFactura.getTxtcorreo().setEnabled(false);
        vistaFactura.getTxttelefono().setEnabled(false);
        vistaFactura.getTxtcorreo().setEnabled(false);
    }

    public KeyListener validarNumeros(JTextField numero) { // metodo para validar el ingreso de numeros 
        KeyListener kn = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char val = e.getKeyChar();
                if (val < '0' || val > '9') {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        return kn;
    }

}
