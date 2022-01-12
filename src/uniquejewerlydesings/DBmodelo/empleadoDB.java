/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.DBmodelo;

import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author corin
 */
public class empleadoDB extends empleado{
    Conexion conn;
    PreparedStatement ps;
    ResultSet re = null;
    //Variable asignada para el valor de la consulta SQL
    String sql = "";
// metodo para ingresar una empresa 

   
    public int id_autoempre() {
        conn = new Conexion();
        int id = 1;
        try {
            ps = conn.conectarBD().prepareStatement("select max(id_empleado) from empleado");
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
