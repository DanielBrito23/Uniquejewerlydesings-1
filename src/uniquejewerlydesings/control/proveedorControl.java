/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import uniquejewerlydesings.DBmodelo.proveedorDB;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.proveedor;
import uniquejewerlydesings.vista.RegistroProveedor;

/**
 *
 * @author corin
 */
public class proveedorControl {

    private proveedorDB proveedorDB;
    private RegistroProveedor vistaProveedor;

    public proveedorControl(proveedorDB proveedorDB, RegistroProveedor vistaProveedor) {

        this.proveedorDB = proveedorDB;
        this.vistaProveedor = vistaProveedor;
    }

    public void iniciarControl() {
        //abrir la ventana
        //vistaProveedor.setVisible(true);
        vistaProveedor.setLocationRelativeTo(null);
//        vistaProveedor.getTxtidpersona().setText(String.valueOf(idper()));
        //acciones a los botones de la vistaPersona
        vistaProveedor.getBtnGuardar().addActionListener(e -> ingreso());
        vistaProveedor.getBtnCancelar().addActionListener(e -> limpiarCampos());
        // metodos de inicio
        vistaProveedor.getBtnbuscar().addActionListener(e -> buscar());
        vistaProveedor.getTxtIdPerson().setVisible(false);
        incrementarId();
    }

    public void buscar() {
        if (vistaProveedor.getTxtIdpersona().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data not found");
        } else {
            persona p = proveedorDB.buscarPersonaId(vistaProveedor.getTxtIdpersona().getText());
            vistaProveedor.getTxtNombres().setText(p.getNombres() + "");
            vistaProveedor.getTxtIdPerson().setText(p.getId_persona() + "");
        }
    }

    public void ingreso() {
        if (vistaProveedor.getTxtIdpersona().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Empty data please enter");
        } else {
            proveedorDB.setId_proveedor(Integer.parseInt(vistaProveedor.getTxtIdProveedor().getText()));
            proveedorDB.setId_persona(Integer.parseInt(vistaProveedor.getTxtIdPerson().getText()));
            proveedorDB.insertarProveedor();
            limpiarCampos();
            incrementarId();
        }
    }

    public int idpro() {
        int id = proveedorDB.id_auto();
        return id;
    }

    public void limpiarCampos() {
        vistaProveedor.getTxtIdPerson().setText("");
        vistaProveedor.getTxtNombres().setText("");
        vistaProveedor.getTxtIdpersona().setText("");
    }

    
    public void incrementarId(){
        vistaProveedor.getTxtIdProveedor().setText(String.valueOf(idpro()));
    }
}
