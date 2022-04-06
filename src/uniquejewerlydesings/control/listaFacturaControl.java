/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import uniquejewerlydesings.DBmodelo.cuerpoFacturaDB;
import javax.swing.table.TableColumnModel;
import uniquejewerlydesings.vista.ListaFactura;

/**
 *
 * @author corin
 */
public class listaFacturaControl {

    private cuerpoFacturaDB cuerpoBD;
    private TableColumnModel columnModel;
    private ListaFactura vistaLista;
//    public static int enviar = 0;
//    int num = 0;

    public listaFacturaControl(cuerpoFacturaDB cuerpoBD, ListaFactura vistaLista) {
        this.cuerpoBD = cuerpoBD;
        this.vistaLista = vistaLista;
    }

    public void inciaControl() {
        vistaLista.setVisible(true);
        vistaLista.setLocationRelativeTo(null);
        vistaLista.setTitle("List Invoice");
        cuerpoBD = new cuerpoFacturaDB();
        columnModel = vistaLista.getJtblista().getColumnModel();
        listar();
    }

    private void listar() {
        vistaLista.getJtblista().setModel(cuerpoBD.getDatosInventario());
        columnModel.getColumn(1).setPreferredWidth(400);
    }
}
