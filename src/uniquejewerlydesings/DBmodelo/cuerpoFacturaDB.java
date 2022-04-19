/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.DBmodelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uniquejewerlydesings.conexion.Conexion;
import static uniquejewerlydesings.control.facturaControl.id_producto;
import uniquejewerlydesings.modelo.cuerpoFactura;
import java.sql.SQLException;

/**
 *
 * @author corin
 */
public class cuerpoFacturaDB extends cuerpoFactura {

    private Conexion conecta = new Conexion();
    private DefaultTableModel DT;
    private PreparedStatement PS = null;
    private ResultSet RS;

    private final String SQL_SELECT_INVENTARIO = "SELECT e.fecha ||' - '|| e.hora as Fecha, p.cedula ,p.nombres,"
            + "e.id_encabezado,p.direccion, p.telefono, p.correo \n"
            + "FROM persona p\n"
            + "JOIN cliente cl ON p.id_persona=cl.id_persona \n"
            + "JOIN encabezado e ON e.id_cliente=cl.id_cliente \n"
            + "ORDER BY 1 desc";

    public cuerpoFacturaDB() {

    }

    public cuerpoFacturaDB(int id_cuerpo, double total, double abono, double valor_pendiente, String reparacion, double total_reparacion, uniquejewerlydesings.modelo.producto producto, int id_encabezado, int id_cliente, int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        super(id_cuerpo, total, abono, valor_pendiente, reparacion, total_reparacion, producto, id_encabezado, id_cliente, id_persona, cedula, nombres, direccion, telefono, correo);
    }

    public boolean insertarCuerpo(String codProd, int idCuerpo, int idEncabezado,cuerpoFacturaDB BD) {

        String sql = "insert into cuerpo_factura (id_cuerpo, id_encabezado, id_producto, total, abono, valor_pendiente,reparacion,total_reparacion) "
                + "values (" + idCuerpo + ", '" + idEncabezado+ "','" + codProd + "','" + BD.getTotal() + "','" + BD.getAbono() + "','" + BD.getValor_pendiente() + "','" + BD.getReparacion() + "','" + BD.getTotal_reparacion() + "');";

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

// ponen los nombres en la tabla
    private DefaultTableModel setTitulosInventario() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
//        DT.addColumn("Identification");
        DT.addColumn("Date");
        DT.addColumn("Identification");
        DT.addColumn("Names");
        DT.addColumn("IDFACTURA");
        DT.addColumn("direccion");
        DT.addColumn("telefono");
        DT.addColumn("correo");
        return DT;
    }
// con la consulta agrega a la tabla los datos
// muestre numero de cedula antes de que me muestra antes 
    //

    public DefaultTableModel getDatosInventario() {
        try {
            setTitulosInventario();
            PS = conecta.conectarBD().prepareStatement(SQL_SELECT_INVENTARIO);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getString(6);
                fila[6] = RS.getString(7);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;

        }
        return DT;
    }

}
