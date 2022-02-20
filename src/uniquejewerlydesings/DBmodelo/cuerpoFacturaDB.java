/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.DBmodelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import uniquejewerlydesings.conexion.Conexion;
import uniquejewerlydesings.modelo.cuerpoFactura;

/**
 *
 * @author corin
 */
public class cuerpoFacturaDB extends cuerpoFactura {

    private Conexion conecta = new Conexion();

    public cuerpoFacturaDB() {
    }

    public cuerpoFacturaDB(int id_cuerpo, double total, double abono, double valor_pendiente, String reparacion, double total_reparacion, uniquejewerlydesings.modelo.producto producto, int id_encabezado, int id_cliente, int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_cuerpo, total, abono, valor_pendiente, reparacion, total_reparacion, producto, id_encabezado, id_cliente, id_persona, cedula, nombres, direccion, telefono, correo);
    }

    public boolean insertarCuerpo() {

        String sql = "insert into cuerpo_factura (id_cuerpo, id_encabezado, id_producto, total, abono, valor_pendiente,reparacion,total_reparacion) "
                + "values (" + getId_cuerpo() + ", '" + getId_encabezado() + "', '" + getProducto()+ "','" + getTotal() + "','" + getAbono()+ "','" + getValor_pendiente()+ "','" + getReparacion() +"',"+getTotal_reparacion()+ "');";

        System.out.println("insert Cuerpo: " + sql);
        PreparedStatement ps = conecta.getPs(sql);

        try {
            conecta.noQuery(ps);
            return true;
        } catch (Exception e) {
            System.out.println("Error insertar cuerpo: " + e.getMessage());
            return false;
        }
    }
     public int id_autoCuerpo() {
        PreparedStatement ps = null;
        ResultSet re = null;
        int id = 1;
        try {
            ps = conecta.conectarBD().prepareStatement("select max(id_cuerpo) from cuerpo_factura");
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
