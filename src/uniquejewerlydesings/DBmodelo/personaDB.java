/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.DBmodelo;

import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class personaDB extends persona {
private Conexion conecta = new Conexion();

    public personaDB() {
    }

    public personaDB(int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_persona, cedula, nombres, direccion, telefono, correo);
    }

    public boolean insertarPersona() {

        String sql = "insert into persona (id_persona, cedula, nombres, direccion, telefono, correo) "
                + "values (" + getId_persona() + ", '" + getCedula() + "', '" + getNombres() + "','" + getDireccion() + "','" + getTelefono() + "','" + getCorreo() + "');";

        System.out.println("insert Persona: " + sql);
        PreparedStatement ps = conecta.getPs(sql);

        try {
            conecta.noQuery(ps);
            return true;
        } catch (Exception e) {
            System.out.println("Error insertar persona: "+e.getMessage());
            return false;
        }
    }

    public List<persona> listaPersonas() throws SQLException {

        System.out.println("Entra a lista sql");
        List<persona> listaPersonas = new ArrayList<persona>();
        String sql = "select * from persona  WHERE estado='a'";
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
            return listaPersonas;
        } catch (SQLException e) {
            System.out.println("error en la consulta de la tabla" + e.getMessage().toString());
            JOptionPane.showMessageDialog(null, "error tabla: " + e.getMessage());
            return null;
        }
    }
    
    
     public List<persona> buscar(String aguja)  {
        List<persona> listaPersonas = new ArrayList();
        
        String sql = "SELECT * FROM persona WHERE estado='a' and nombres LIKE '"+aguja+"%' or cedula LIKE '"+aguja+"%'";
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
            return listaPersonas;
           
        } catch (SQLException ex) {
            
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
      public boolean eliminarPersona() {
        String sql = "UPDATE persona SET estado = 'd' WHERE id_persona='" + getId_persona()+ "'";
        System.out.println(getId_persona());
        if (conecta.noQuery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }
     
    public int id_autoper() {
        PreparedStatement ps = null;
        ResultSet re = null;
        int id = 1;
        try {
            ps = conecta.conectarBD().prepareStatement("select max(id_persona) from persona");
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
