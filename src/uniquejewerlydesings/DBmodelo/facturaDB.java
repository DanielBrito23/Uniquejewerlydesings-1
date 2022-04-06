/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.DBmodelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.encabezadoFactura;
import uniquejewerlydesings.modelo.persona;

/**
 *
 * @author corin
 */
public class facturaDB extends encabezadoFactura {

    private Conexion conecta = new Conexion();
    

    public facturaDB(int id_encabezado, int id_cliente, int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_encabezado, id_cliente, id_persona, cedula, nombres, direccion, telefono, correo);
    }

    public facturaDB() {

    }

    public List<persona> buscar(String aguja) {
        List<persona> listaPersonas = new ArrayList();

        String sql = "SELECT * FROM persona WHERE cedula LIKE '" + aguja + "%'";
        System.out.println(sql);
        ResultSet rs = conecta.query(sql);
        try {
            while (rs.next()) {
                persona p = new persona();
                p.setId_persona(rs.getInt("id_persona"));
                p.setCedula(rs.getString("cedula"));
                p.setNombres(rs.getString("nombres"));
                p.setCorreo(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("correo"));
                listaPersonas.add(p);
            }
            rs.close();
            System.out.println(listaPersonas);
            return listaPersonas;

        } catch (SQLException ex) {

            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public persona buscarPersonaId(String id) {
        String sql = "Select * from persona where cedula = '" + id + "'";
        ResultSet rs = conecta.query(sql);
        System.out.println("burcar por cedula: " + sql);
        persona p = new persona();
        try {
            if (rs.next()) {
                p.setId_persona(rs.getInt("id_persona"));
                p.setCedula(rs.getString("cedula"));
                p.setNombres(rs.getString("nombres"));
                p.setCorreo(rs.getString("correo"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.out.println("error buscar por id en sql: " + e.getMessage());
        }
        return p;
    }

    public boolean insertarFactura() {

        String sql = "insert into encabezado (id_encabezado, id_cliente) "
                + "values (" + getId_encabezado() + "," + getId_cliente() + ");";

        System.out.println("insert factura: " + sql);
        PreparedStatement ps = conecta.getPs(sql);

        try {
            conecta.noQuery(ps);
            return true;
        } catch (Exception e) {
            System.out.println("Error insertar factura: " + e.getMessage());
            return false;
        }
    }

    public int id_autofactur() {
        PreparedStatement ps = null;
        ResultSet re = null;
        int id = 1;
        try {
            ps = conecta.conectarBD().prepareStatement("select max(id_encabezado) from encabezado");
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
