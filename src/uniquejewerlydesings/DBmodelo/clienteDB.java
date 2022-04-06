/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.DBmodelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.cliente;

/**
 *
 * @author corin
 */
public class clienteDB extends cliente {

    private Conexion conecta = new Conexion();
    

    public clienteDB() {
    }

    public clienteDB(int id_cliente, int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_cliente, id_persona, cedula, nombres, direccion, telefono, correo);
    }

    public boolean insertarCliente() {

        String sql = "insert into cliente (id_persona, id_cliente) "
                + "values (" + getId_persona() + ", " + getId_cliente() + ");";

        System.out.println("insert Cliente: " + sql);
        PreparedStatement ps = conecta.getPs(sql);

        try {
            conecta.noQuery(ps);
            return true;
        } catch (Exception e) {
            System.out.println("Error insertar cliente: " + e.getMessage());
            return false;
        }
 
    }
    
    public int id_autoCli() {
        PreparedStatement ps = null;
        ResultSet re = null;
        int id = 1;
        try {
            ps = conecta.conectarBD().prepareStatement("select max(id_cliente) from cliente");
            re = ps.executeQuery();
            while (re.next()) {
                id = re.getInt(1) + 1;
            }
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        } finally {
            try {
                ps.close();
                re.close();
            } catch (Exception e) {
            }
        }
        return id;
    }
}
