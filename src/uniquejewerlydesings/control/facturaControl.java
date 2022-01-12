/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import uniquejewerlydesings.vista.Factura;
import uniquejewerlydesings.vista.PersonaIngreso;

/**
 *
 * @author LENOVO
 */
public class facturaControl {
    
    Factura vista = new Factura();
    PersonaIngreso vistaPersona = new PersonaIngreso();
    
    public void iniciarControl (){
        vista.getBtnNewUser().addActionListener(e -> formularioPersona());
        ventana();
    }
    
    public void ventana (){
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setTitle("Invoice");
    }
    
    public void formularioPersona(){
        vistaPersona.setVisible(true);
        vistaPersona.setLocationRelativeTo(null);
        vistaPersona.setTitle("New Customer");
    }
    
}
