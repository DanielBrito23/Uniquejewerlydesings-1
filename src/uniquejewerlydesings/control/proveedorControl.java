/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import uniquejewerlydesings.DBmodelo.proveedorDB;
import uniquejewerlydesings.modelo.proveedor;
import uniquejewerlydesings.vista.RegistroProveedor;

/**
 *
 * @author corin
 */
public class proveedorControl {

    private proveedor proveedorModelo;
    private proveedorDB proveedorDB;
    private RegistroProveedor vistaProveedor;

    public proveedorControl(proveedor proveedorModelo, proveedorDB proveedorDB, RegistroProveedor vistaProveedor) {
        this.proveedorModelo = proveedorModelo;
        this.proveedorDB = proveedorDB;
        this.vistaProveedor = vistaProveedor;
    }

    public void iniciarControl() {
        //abrir la ventana
        vistaProveedor.setVisible(true);
        vistaProveedor.setLocationRelativeTo(null);
//        vistaProveedor.getTxtID().setText(String.valueOf(idpro()));
//        vistaProveedor.getTxtidpersona().setText(String.valueOf(idper()));
        //acciones a los botones de la vistaPersona
        vistaProveedor.getBtnGuardar().addActionListener(e -> ingreso());
    }

    public void ingreso() {
//        if (vistaProveedor.getTxtCedula().getText().equals("") || vistaProveedor.getTxtNombres().getText().equals("") || vistaProveedor.getTxtCorreo().getText().equals("")
//                || vistaProveedor.getTxtTelefono().getText().equals("") || vistaProveedor.getTxtCorreo().getText().equals("")) {
//            JOptionPane.showMessageDialog(null, "Empty data please enter");
//        } else {
//            proveedorDB.setId_proveedor(Integer.parseInt(vistaProveedor.getTxtID().getText()));
//            proveedorDB.setCedula(vistaProveedor.getTxtCedula().getText());
//            proveedorDB.setNombres(vistaProveedor.getTxtNombres().getText());
//            proveedorDB.setDireccion(vistaProveedor.getTxtDireccion().getText());
//            proveedorDB.setTelefono(vistaProveedor.getTxtTelefono().getText());
//            proveedorDB.setCorreo(vistaProveedor.getTxtCorreo().getText());
//            if (proveedorDB.insertarProveedor()) {
//                JOptionPane.showMessageDialog(null, "Added successfully");
//            } else {
//                JOptionPane.showMessageDialog(null, "Data entry error");
//            }
//        }
    }

//    public int idpro() {
//        int id = proveedorDB.id_auto();
//        return id;
//    }
//
//    public int idper() {
//        int id = proveedorDB.id_autoper();
//        return id;
//    }
}
