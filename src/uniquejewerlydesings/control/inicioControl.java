/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import javax.swing.JOptionPane;
import uniquejewerlydesings.vista.Inicio;
import uniquejewerlydesings.vista.MenuPrincipal;

/**
 *
 * @author LENOVO
 */
public class inicioControl {

private menuControl menuControl;
    private MenuPrincipal vistaMenu;
    private Inicio vistaInicio;

    
    menuControl m = new menuControl();
    
    public inicioControl(menuControl menuControl, MenuPrincipal vistaMenu, Inicio vistaInicio) {
        this.menuControl = menuControl;
        this.vistaMenu = vistaMenu;
        this.vistaInicio = vistaInicio;

        vistaInicio.setVisible(true);
        vistaInicio.setLocationRelativeTo(null);
    }

    public void iniciaControl() {
        vistaInicio.getBtnstart().addActionListener(e -> inicioMenu());
        vistaInicio.getBtnstart().addActionListener(e -> cierraInicio());
        
        
    }
    
    public void inicioMenu (){  
        m.iniciarControl();
    }
    
    public void cierraInicio (){  
        vistaInicio.dispose();
    }
}
