/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import javax.swing.JOptionPane;
import uniquejewerlydesings.DBmodelo.empleadoDB;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.validacion;
import uniquejewerlydesings.vista.VistaNuevoEmpleado;

/**
 *
 * @author corin
 */
public class empleadoControl extends validacion{

    private final empleadoDB modelo;
    private final VistaNuevoEmpleado vista;

    public empleadoControl(empleadoDB modelo, VistaNuevoEmpleado vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciarControl() {
        // botones

        vista.getBtnGuardar().addActionListener(e -> guardarEmple());
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getBtnCancelar().addActionListener(e -> cancelar());
        
        // adicional
        validarCampos();
        ventana();
        vista.getTxtIdEmple().setText(String.valueOf(idEmpleado()));
    }

    public void guardarEmple() {
        if (vista.getTxtCargo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Empty data please enter");
        } else {
            try {
                modelo.setId_empleado(Integer.parseInt(vista.getTxtIdEmple().getText()));
                modelo.setCedula(vista.getTxtIdPersonaEmple().getText());
                modelo.setCargo(vista.getTxtCargo().getText());
                modelo.insertarEmpleado();
                limpiarCampos();
                vista.setVisible(false);
                JOptionPane.showMessageDialog(null, "Added successfully");
            } catch (Exception e) {
                System.out.println("Error ingresar empleado: " + e.getMessage());
            }
        }
    }

    public void buscar() {
        if (vista.getTxtIdPersonaEmple().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data not found");
        } else {
            persona p = modelo.buscarPersonaId(vista.getTxtIdPersonaEmple().getText());
            vista.getTxtRefeNombres().setText(p.getNombres() + "");
        }
    }

    public void limpiarCampos(){
        vista.getTxtCargo().setText("");
        vista.getTxtIdPersonaEmple().setText("");
        vista.getTxtRefeNombres().setText("");
    }
    
    public void ventana() {
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setTitle("Registration");
    }

    public void cancelar(){
        vista.setVisible(false);
    }
    public int idEmpleado() {
        int id = modelo.id_autoemple();
        return id;
    }

      public void validarCampos() {
        vista.getTxtCargo().addKeyListener(validarLetras(vista.getTxtCargo()));
        vista.getTxtIdPersonaEmple().addKeyListener(validarNumeros(vista.getTxtIdPersonaEmple()));
    }
}
