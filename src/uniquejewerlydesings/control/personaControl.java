/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import uniquejewerlydesings.modelo.validacion;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.DBmodelo.personaDB;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.vista.ListaPersonas;
import uniquejewerlydesings.vista.PersonaIngreso;

/**
 *
 * @author LENOVO
 */
public class personaControl extends validacion{

    private persona personaModelo;
    private personaDB personaDB;
    private PersonaIngreso vistaPersona;
    private ListaPersonas tablaPersona;
    private validacion b;
    DefaultTableModel modelo = new DefaultTableModel();

    public personaControl(persona personaModelo, personaDB personaDB, PersonaIngreso vistaPersona) {
        this.personaModelo = personaModelo;
        this.personaDB = personaDB;
        this.vistaPersona = vistaPersona;
    }

    public void iniciarControl() {
        //abrir la ventana
        vistaPersona.setVisible(true);
        vistaPersona.setLocationRelativeTo(null);
        vistaPersona.getTxtID().setText(String.valueOf(idper()));

        //acciones a los botones de la vistaPersona
        vistaPersona.getBtnGuardar().addActionListener(e -> ingresoPersona());
        
        
        validarCampos();
    }

    public void validarCampos() {
          vistaPersona.getTxtNombres().addKeyListener(validarLetras(vistaPersona.getTxtNombres()));
          vistaPersona.getTxtDireccion().addKeyListener(validarLetras(vistaPersona.getTxtDireccion()));
          vistaPersona.getTxtTelefono().addKeyListener(validarCelular(vistaPersona.getTxtTelefono()));
          vistaPersona.getTxtNombres().addKeyListener(validarLetras(vistaPersona.getTxtNombres()));
    }

    public void ingresoPersona() {

        if (vistaPersona.getTxtCedula().getText().equals("") || vistaPersona.getTxtNombres().getText().equals("") || vistaPersona.getTxtCorreo().getText().equals("")
                || vistaPersona.getTxtTelefono().getText().equals("") || vistaPersona.getTxtCorreo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Empty data please enter");
        } else {
            personaDB.setId_persona(Integer.parseInt(vistaPersona.getTxtID().getText()));
            personaDB.setCedula(vistaPersona.getTxtCedula().getText());
            personaDB.setNombres(vistaPersona.getTxtNombres().getText());
            personaDB.setDireccion(vistaPersona.getTxtDireccion().getText());
            personaDB.setTelefono(vistaPersona.getTxtTelefono().getText());
            personaDB.setCorreo(vistaPersona.getTxtCorreo().getText());
            if (personaDB.insertarPersona()) {
                JOptionPane.showMessageDialog(null, "Added successfully");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Data entry error");
            }
        }
      
    }

    public int idper() {
        int id = personaDB.id_autoper();
        return id;
    }
    
    public void limparCampos() {
        vistaPersona.getTxtCedula().setText("");

        vistaPersona.getTxtNombres().setText("");

        vistaPersona.getTxtCorreo().setText("");

        vistaPersona.getTxtTelefono().setText("");

        vistaPersona.getTxtDireccion().setText("");
    }

}
