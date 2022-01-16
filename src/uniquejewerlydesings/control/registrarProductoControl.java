/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import javax.swing.JOptionPane;
import uniquejewerlydesings.DBmodelo.productoDB;
import uniquejewerlydesings.extra.PlaceHolder;
import uniquejewerlydesings.modelo.validacion;
import uniquejewerlydesings.vista.RegistroProductos;

/**
 *
 * @author LENOVO
 */
public class registrarProductoControl extends validacion {

    private RegistroProductos vista;
    private productoDB modelo;

    public registrarProductoControl(RegistroProductos vista, productoDB modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void iniciarControl() {
        abrirVentana();
        validarCampos();
        placeHolder();
        vista.getTxtID().setText(String.valueOf(idProducto()));

        //acciones de los botones
        vista.getBtnGuardar().addActionListener(e -> nuevoProducto());
    }

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
                modelo.insertarProducto();
                JOptionPane.showMessageDialog(null, "Added successfully");
                limpiarCampos();
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
        vista.setVisible(false);
    }

    public void validarCampos() {
        vista.getTxtCalculo().addKeyListener(validarNumeros(vista.getTxtCalculo()));
        vista.getTxtDescripcio().addKeyListener(validarLetras(vista.getTxtDescripcio()));
        vista.getTxtTipo().addKeyListener(validarLetras(vista.getTxtTipo()));
    }

    public void abrirVentana() {
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setTitle("Product Registration");
    }

    public void placeHolder() {
        PlaceHolder txtbuscar = new PlaceHolder("dd/mm/AA", vista.getTxtFecha());
    }
}
