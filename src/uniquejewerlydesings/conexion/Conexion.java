/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.conexion;

/**
 *
 * @author LENOVO
 */
//holii
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author icrv9
 */
public class Conexion {

   private final static String cadenaConexion = "jdbc:postgresql://localhost:5432/joyeria";
    private final static String pgUsuario = "postgres";
    private final static String pgPass = "1256";
    private Connection con;//conexion
    private Statement st;// comando:sql
    private ResultSet rs;//Resultados de la consulta

    public Conexion() {
        //fijar clase de conexion
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(cadenaConexion, pgUsuario, pgPass);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public PreparedStatement getPs(String sql) {
        try {
            return con.prepareStatement(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SQLException noQuery(PreparedStatement ps) {
        try {
            int res = ps.executeUpdate();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        }
    }

    public SQLException noQuery(String sql) {
        try {
            st = con.createStatement();
            st.execute(sql);
            st.close();
            return null; //al momento de agregar el SQLEXception se debe que si no hay problema salga null
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return ex; //al momento de agregar el SQLEXception se debe que si hay problema salga error "ex"
        }
    }

    public ResultSet query(String sql) {
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Connection conectarBD() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con = DriverManager.getConnection(cadenaConexion, pgUsuario, pgPass);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
