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
import uniquejewerlydesings.modelo.cuerpoFactura;
import uniquejewerlydesings.modelo.encabezadoFactura;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.producto;

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

        String sql = "insert into encabezado (id_encabezado, id_cliente, fecha, hora) "
                + "values (" + getId_encabezado() + "," + getId_cliente() + ",current_date, CURRENT_TIME);";

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

    public ArrayList<Object> buscarFactura(String cedula, String idEncabezado) {
        ArrayList<Object> datos = new ArrayList<>();
        String sql = "select e.id_encabezado,prod.id_producto, p.cedula, p.id_persona, p.nombres,p.direccion,p.telefono,p.correo, \n"
                + "cf.reparacion,prod.descripcion,\n"
                + "cf.total_reparacion,cf.abono,cf.valor_pendiente, prod.fecha_compra \n"
                + "FROM persona p \n"
                + "JOIN cliente cl on p.id_persona=cl.id_persona \n"
                + "JOIN encabezado e on e.id_cliente= cl.id_cliente\n"
                + "JOIN cuerpo_factura cf on cf.id_encabezado=e.id_encabezado\n"
                + "JOIN producto prod on cf.id_producto = prod.id_producto\n"
                + "where p.cedula = '" + cedula + "' and cf.id_encabezado=" + idEncabezado;
        ResultSet rs = conecta.query(sql);
        System.out.println("buscar factura: " + sql);
        persona p = new persona();
        encabezadoFactura e = new encabezadoFactura();
        cuerpoFactura cf = new cuerpoFactura();
        producto pro = new producto();

        try {
            if (rs.next()) {
                e.setId_persona(rs.getInt("id_encabezado"));
                pro.setId_producto(rs.getInt("id_producto"));
                p.setCedula(rs.getString("cedula"));
                p.setId_persona(rs.getInt("id_persona"));
                p.setNombres(rs.getString("nombres"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo"));
                cf.setReparacion(rs.getString("reparacion"));
                pro.setDescripcion(rs.getString("descripcion"));
                cf.setTotal_reparacion(rs.getDouble("total_reparacion"));
                cf.setAbono(rs.getDouble("abono"));
                cf.setValor_pendiente(rs.getDouble("valor_pendiente"));

            }
        } catch (SQLException ex) {
            System.out.println("error buscar por id en sql: " + ex.getMessage());
        }
        datos.add(p);
        datos.add(e);
        datos.add(cf);
        datos.add(pro);
        return datos;
    }

    public List<producto> llenarTablaFactura(String cedula, String idEncabezado) {
        ArrayList<producto> datos = new ArrayList<>();
        String sql = "select prod.id_producto, prod.descripcion, prod.cantidad, prod.precio_unitario \n"
                + "FROM persona p \n"
                + "JOIN cliente cl on p.id_persona=cl.id_persona \n"
                + "JOIN encabezado e on e.id_cliente= cl.id_cliente\n"
                + "JOIN cuerpo_factura cf on cf.id_encabezado=e.id_encabezado\n"
                + "JOIN producto prod on cf.id_producto = prod.id_producto\n"
                + "where p.cedula = '" + cedula + "' and cf.id_encabezado=" + idEncabezado;
        ResultSet rs = conecta.query(sql);
        System.out.println("buscar factura: " + sql);

        try {
            while (rs.next()) {
                producto pro = new producto();
                pro.setId_producto(rs.getInt("id_producto"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setPrecio_unitario(rs.getDouble("precio_unitario"));
                datos.add(pro);
            }
        } catch (SQLException ex) {
            System.out.println("error buscar por id en sql: " + ex.getMessage());
        }
        return datos;
    }

    
    public boolean editarPersonas(String cedula, personaDB pDB) {
        String sql = "UPDATE persona SET ";
        sql += "nombres='" + pDB.getNombres() + "', ";
        sql += "direccion='" + pDB.getDireccion() + "', ";
        sql += "telefono='" + pDB.getTelefono() + "', ";
        sql += "correo='" + pDB.getCorreo() + "'"
                + " WHERE cedula ='" + cedula + "' AND estado = 'a'";

        System.out.println("update: " + sql);
        if (conecta.noQuery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void editarProductos(){
        String sql = "";
    }

    public boolean QuitarProducto(int idProd, int idEncabezado) {
        String sql = "DELETE FROM cuerpo_factura cf\n"
                + "     USING encabezado en \n"
                + "WHERE cf.id_encabezado = en.id_encabezado AND\n"
                + "      cf.id_producto ="+idProd+" and en.id_encabezado =" + idEncabezado;
        if (conecta.noQuery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }
}
