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
import javax.swing.JOptionPane;
import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.producto;
import uniquejewerlydesings.modelo.proveedor;
import uniquejewerlydesings.vista.RegistroProveedor;

/**
 *
 * @author corin
 */
public class proveedorDB extends proveedor {

    Conexion conn;
    PreparedStatement ps;
    ResultSet re = null;
    //Variable asignada para el valor de la consulta SQL
    String sql = "";
    String sql1 = "";

// metodo para ingresar una persona 
    public proveedorDB(String cedula, String nombres, String correo) {
        super(cedula, nombres, correo);
    }

    public proveedorDB() {
    }

    public boolean insertarProveedor() {

        conn = new Conexion();
        sql = "insert into proveedor (id_proveedor,id_persona)"
                + "values (" + getId_proveedor() + "," + getId_persona() + ");";
        System.out.println("insert Proveedor: " + sql);

        PreparedStatement ps = conn.getPs(sql);

        if (conn.noQuery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }

    public int id_auto() {
        conn = new Conexion();
        int id = 1;
        try {
            ps = conn.conectarBD().prepareStatement("select max(id_proveedor) from proveedor");
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

    public persona buscarPersonaId(String id) {
        String sql = "Select * from persona where cedula = '" + id + "'";
        ResultSet rs = conn.query(sql);
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

    public List<proveedor> listaProveedores() {
        System.out.println("entra prove sql");
        List<proveedor> proveedores = new ArrayList<proveedor>();
        String sql = "select per.cedula, per.nombres, per.correo from  proveedor pro INNER JOIN persona per on per.id_persona = pro.id_persona";
        
        try {
            ResultSet rs = conn.query(sql);
            while (rs.next()) {
                proveedor pro = new proveedor();
                pro.setCedula(rs.getString("cedula"));
                pro.setNombres(rs.getString("nombres"));
                pro.setCorreo(rs.getString("correo"));
                proveedores.add(pro);
                System.out.println("lista pro:" + pro);
            }
            rs.close();
            return proveedores;
        } catch (Exception e) {
            System.out.println("error en la lista pro: " + e.getMessage());
            return null;
        }

    }

}
