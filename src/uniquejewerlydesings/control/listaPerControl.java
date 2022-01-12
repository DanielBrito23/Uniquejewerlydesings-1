/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.DBmodelo.personaDB;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.vista.ListaPersonas;

/**
 *
 * @author corin
 */
public class listaPerControl {

    ListaPersonas vistaLis;
    DefaultTableModel modeloTab;
    personaDB db;
    persona per;
    int fila;

    public void iniciarControl() {
        vistaLis.setVisible(true);
        vistaLis.setLocationRelativeTo(null);
//        listar();

//        vistaLis.getBtncargar().addActionListener(e -> listar());

    }

    public listaPerControl(ListaPersonas vistaLis, personaDB db, persona per) {
        this.vistaLis = vistaLis;
        this.db = db;
        this.per = per;
    }

    }
