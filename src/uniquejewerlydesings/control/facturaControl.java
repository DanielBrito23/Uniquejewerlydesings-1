/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import javax.swing.JOptionPane;
import uniquejewerlydesings.DBmodelo.facturaDB;
import uniquejewerlydesings.vista.Factura;
import uniquejewerlydesings.vista.PersonaIngreso;

/**
 *
 * @author LENOVO
 */
public class facturaControl {

     private Factura vistaFactura ;
    private PersonaIngreso vistaPersona ;
    private facturaDB factura ;

    public facturaControl(Factura vistaFactura, PersonaIngreso vistaPersona, facturaDB factura) {
        this.vistaFactura = vistaFactura;
        this.vistaPersona = vistaPersona;
        this.factura = factura;
    }
    
    

    public void iniciarControl() {
        vistaFactura.getBtnNewUser().addActionListener(e -> formularioPersona());
        vistaFactura.getBtnbuscar().addActionListener(e -> buscar());
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
    }

    public void buscar() {
        if (vistaFactura.getTxtid().getText().equals("")) {
            JOptionPane.showConfirmDialog(null, "el campo esta vacio");
        } else {
            factura.buscar(vistaFactura.getTxtid().getText());
           vistaFactura.getTxtcedula().setText(factura.getCedula());
           vistaFactura.getTxtnombres().setText(factura.getNombres());
       
        }
    }

}

