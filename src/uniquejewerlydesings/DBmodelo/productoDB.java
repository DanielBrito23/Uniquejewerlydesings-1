/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.DBmodelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.modelo.producto;

/**
 *
 * @author LENOVO
 */
public class productoDB extends producto {

    private Conexion conecta = new Conexion();

    public productoDB() {
    }

    public productoDB(int id_producto, int calculo_utilidad, String descripcion, Date fecha, int cantidad, double peso_metal, String tipo_metal, double precio_unitario, String tipo_producto) {
        super(id_producto, calculo_utilidad, descripcion, fecha, cantidad, peso_metal, tipo_metal, precio_unitario, tipo_producto);
    }

    public List<producto> listaProductos() {

        System.out.println("Entra a lista sql");
        List<producto> listaProducto = new ArrayList<producto>();
        String sql = "select * from producto WHERE estado = 'a'";
        ResultSet rs = conecta.query(sql);
        try {
            while (rs.next()) {
                producto pro = new producto();
                pro.setId_producto(rs.getInt("id_producto"));
                pro.setCalculo_utilidad(rs.getInt("calculo_utilidad"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setFecha(rs.getDate("fecha_compra"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setPeso_metal(rs.getInt("peso_metal"));
                pro.setTipo_metal(rs.getString("tipo_metal"));
                pro.setPrecio_unitario(rs.getDouble("precio_unitario"));
                listaProducto.add(pro);
            }
            rs.close();
            return listaProducto;
        } catch (SQLException e) {
            System.out.println("error en la consulta de la tabla" + e.getMessage().toString());
            JOptionPane.showMessageDialog(null, "error tabla: " + e.getMessage());
            return null;
        }
    }

    public List<producto> buscar(String aguja)throws SQLException{
        List<producto> listaProducto = new ArrayList();
        String sql = "SELECT * FROM producto WHERE estado='a' and tipo_metal LIKE '"+aguja+"%' or descripcion LIKE '"+aguja+"%'";
         System.out.println(sql);
         ResultSet rs = conecta.query(sql);
         try {
            while (rs.next()) {
                producto pro = new producto();
                pro.setId_producto(rs.getInt("id_producto"));
                pro.setCalculo_utilidad(rs.getInt("calculo_utilidad"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setFecha(rs.getDate("fecha_compra"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setPeso_metal(rs.getInt("peso_metal"));
                pro.setTipo_metal(rs.getString("tipo_metal"));
                pro.setPrecio_unitario(rs.getDouble("precio_unitario"));
                listaProducto.add(pro);
            }
            rs.close();
            return listaProducto;
        } catch (SQLException e) {
            System.out.println("error en la consulta de la tabla" + e.getMessage().toString());
            JOptionPane.showMessageDialog(null, "error tabla: " + e.getMessage());
            return null;
        }
    }
    
    public boolean eliminar() {
        String sql = "UPDATE producto SET estado = 'd' WHERE id_producto='" + getId_producto()+ "'";
        System.out.println(getId_producto());
        if (conecta.noQuery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }

}
