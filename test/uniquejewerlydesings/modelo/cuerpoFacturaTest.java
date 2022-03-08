/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author corin
 */
public class cuerpoFacturaTest {
    
    public cuerpoFacturaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getProducto method, of class cuerpoFactura.
     */
    @Test
    public void testGetProducto() {
        System.out.println("getProducto");
        cuerpoFactura instance = new cuerpoFactura();
        producto expResult = null;
        producto result = instance.getProducto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProducto method, of class cuerpoFactura.
     */
    @Test
    public void testSetProducto() {
        System.out.println("setProducto");
        producto producto = null;
        cuerpoFactura instance = new cuerpoFactura();
        instance.setProducto(producto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId_cuerpo method, of class cuerpoFactura.
     */
    @Test
    public void testGetId_cuerpo() {
        System.out.println("getId_cuerpo");
        cuerpoFactura instance = new cuerpoFactura();
        int expResult = 0;
        int result = instance.getId_cuerpo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId_cuerpo method, of class cuerpoFactura.
     */
    @Test
    public void testSetId_cuerpo() {
        System.out.println("setId_cuerpo");
        int id_cuerpo = 0;
        cuerpoFactura instance = new cuerpoFactura();
        instance.setId_cuerpo(id_cuerpo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReparacion method, of class cuerpoFactura.
     */
    @Test
    public void testGetReparacion() {
        System.out.println("getReparacion");
        cuerpoFactura instance = new cuerpoFactura();
        String expResult = "";
        String result = instance.getReparacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReparacion method, of class cuerpoFactura.
     */
    @Test
    public void testSetReparacion() {
        System.out.println("setReparacion");
        String reparacion = "";
        cuerpoFactura instance = new cuerpoFactura();
        instance.setReparacion(reparacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotal_reparacion method, of class cuerpoFactura.
     */
    @Test
    public void testGetTotal_reparacion() {
        System.out.println("getTotal_reparacion");
        cuerpoFactura instance = new cuerpoFactura();
        double expResult = 0.0;
        double result = instance.getTotal_reparacion();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotal_reparacion method, of class cuerpoFactura.
     */
    @Test
    public void testSetTotal_reparacion() {
        System.out.println("setTotal_reparacion");
        double total_reparacion = 0.0;
        cuerpoFactura instance = new cuerpoFactura();
        instance.setTotal_reparacion(total_reparacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotal method, of class cuerpoFactura.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        cuerpoFactura instance = new cuerpoFactura();
        double expResult = 0.0;
        double result = instance.getTotal();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotal method, of class cuerpoFactura.
     */
    @Test
    public void testSetTotal() {
        System.out.println("setTotal");
        double total = 0.0;
        cuerpoFactura instance = new cuerpoFactura();
        instance.setTotal(total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAbono method, of class cuerpoFactura.
     */
    @Test
    public void testGetAbono() {
        System.out.println("getAbono");
        cuerpoFactura instance = new cuerpoFactura();
        double expResult = 0.0;
        double result = instance.getAbono();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAbono method, of class cuerpoFactura.
     */
    @Test
    public void testSetAbono() {
        System.out.println("setAbono");
        double abono = 0.0;
        cuerpoFactura instance = new cuerpoFactura();
        instance.setAbono(abono);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValor_pendiente method, of class cuerpoFactura.
     */
    @Test
    public void testGetValor_pendiente() {
        System.out.println("getValor_pendiente");
        cuerpoFactura instance = new cuerpoFactura();
        double expResult = 0.0;
        double result = instance.getValor_pendiente();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValor_pendiente method, of class cuerpoFactura.
     */
    @Test
    public void testSetValor_pendiente() {
        System.out.println("setValor_pendiente");
        double valor_pendiente = 0.0;
        cuerpoFactura instance = new cuerpoFactura();
        instance.setValor_pendiente(valor_pendiente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
