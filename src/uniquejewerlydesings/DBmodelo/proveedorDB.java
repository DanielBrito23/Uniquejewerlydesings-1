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
import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.persona;
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
    public boolean insertarProveedor() {

        conn = new Conexion();
        sql = "insert into proveedor (id_proveedor,id_producto,nombre_proveedor,correo_proveedor,telefono,direccion)"
                + "values (" + getId_proveedor() + "," + getId_producto()+  "," + getNombre_proveedor()+  "," + getCorreo_proveedor()+  "," + getTelefono()+  "," + getDireccion()+ ");"; 
        System.out.println("insert Persona: " + sql);
   
        PreparedStatement ps = conn.getPs(sql);
        
        if (conn.noQuery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public int id_auto(){
        conn = new Conexion();
        int id=1;
        try {
            ps=conn.conectarBD().prepareStatement("select max(id_proveedor) from proveedor");
             re=ps.executeQuery();
        while(re.next()){
            id=re.getInt(1)+1;
        }
        } catch (Exception e) {
            System.out.println("error"+e.getMessage());
        }
        finally{
            try {
                ps.close();
                re.close();
            } catch (Exception e) {
            }
        }
        return id;
    }
     public int id_autoper(){
        conn = new Conexion();
        int id=1;
        try {
            ps=conn.conectarBD().prepareStatement("select max(id_persona) from persona");
             re=ps.executeQuery();
        while(re.next()){
            id=re.getInt(1)+1;
        }
        } catch (Exception e) {
            System.out.println("error"+e.getMessage());
        }
        finally{
            try {
                ps.close();
                re.close();
            } catch (Exception e) {
            }
        }
        return id;
    }
    
}
