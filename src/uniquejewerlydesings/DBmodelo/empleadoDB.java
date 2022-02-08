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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import uniquejewerlydesings.modelo.persona;

/**
 *
 * @author corin
 */
public class empleadoDB extends empleado {

    private Conexion conecta = new Conexion();
    Conexion conn;
    PreparedStatement ps;
    ResultSet re = null;
    //Variable asignada para el valor de la consulta SQL
    String sql = "";
// metodo para ingresar una empresa 

    public boolean insertarEmpleado() {

        String sql = "insert into empleado (id_empleado, id_persona, cargo) "
                + "values (" + getId_empleado() + ", '" + getCedula() + "','" + getCargo() + "');";

        System.out.println("insert empleado: " + sql);
        PreparedStatement ps = conecta.getPs(sql);

        try {
            conecta.noQuery(ps);
            return true;
        } catch (Exception e) {
            System.out.println("Error insertar factura: " + e.getMessage());
            return false;
        }
    }

    public persona buscarPersonaId(String cedula) {
        String sql = "Select * from persona where cedula = '" + cedula + "'";
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

    public List<empleado> listaEmpleados() {
        System.out.println("Entra a lista sql");
        List<empleado> listaEmpleados = new ArrayList<empleado>();
        String sql = "select e.id_empleado, e.cargo, per.nombres from empleado e, persona per where e.id_persona = per.cedula";
        ResultSet rs = conecta.query(sql);
        System.out.println(sql);
        try {
            while (rs.next()) {
                empleado emple = new empleado();
                emple.setId_empleado(rs.getInt("id_empleado"));
                emple.setCargo(rs.getString("cargo"));
                emple.setNombres(rs.getString("nombres"));
                listaEmpleados.add(emple);
                System.out.println("result de empleados: "+emple);
            }
            rs.close();
            return listaEmpleados;
        } catch (SQLException e) {
            System.out.println("error en la consulta de la tabla empleados" + e.getMessage().toString());
            JOptionPane.showMessageDialog(null, "error tabla: " + e.getMessage());
            return null;
        }
    }

    public int id_autoemple() {
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
