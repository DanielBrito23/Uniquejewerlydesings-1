/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import uniquejewerlydesings.DBmodelo.clienteDB;
import uniquejewerlydesings.DBmodelo.empleadoDB;
import uniquejewerlydesings.DBmodelo.facturaDB;
import uniquejewerlydesings.DBmodelo.personaDB;
import uniquejewerlydesings.DBmodelo.productoDB;
import uniquejewerlydesings.DBmodelo.proveedorDB;
import uniquejewerlydesings.modelo.persona;
import uniquejewerlydesings.vista.CrudProveedor;
import uniquejewerlydesings.vista.Factura;
import uniquejewerlydesings.vista.ListaPersonas;
import uniquejewerlydesings.vista.ListaProductos;
import uniquejewerlydesings.vista.MenuPrincipal;
import uniquejewerlydesings.vista.PersonaIngreso;
import uniquejewerlydesings.vista.RegistroProductos;
import uniquejewerlydesings.vista.RegistroProveedor;
import uniquejewerlydesings.vista.VistaListaEmple;
import uniquejewerlydesings.vista.VistaNuevoEmpleado;

/**
 *
 * @author LENOVO
 */
public class menuControl {

    MenuPrincipal menu = new MenuPrincipal();

    //Registro de persona
    persona personaModelo = new persona();
    personaDB personaDB = new personaDB();
    PersonaIngreso vistaPersona = new PersonaIngreso();
    ListaPersonas vista = new ListaPersonas();
    personaControl controlPersona = new personaControl(personaModelo, personaDB, vistaPersona);

    listaControl listaPersona = new listaControl(personaDB, vista);

    //instancias para el funcionamiento de la lista de productos
    ListaProductos listaProductos = new ListaProductos();
    productoDB productodb = new productoDB();
    productsStocksControl controlProductos = new productsStocksControl(productodb, listaProductos);

    // instancias para el funcionamiento del boton nuevo producto
    RegistroProductos vistaRegistroPro = new RegistroProductos();
    registrarProductoControl controRegistrarProduc = new registrarProductoControl(vistaRegistroPro, productodb);

    //instancias para la factura 
    Factura vistaFactura = new Factura();
    facturaDB factura = new facturaDB();
    clienteDB clienteDB = new clienteDB();
    facturaControl controlfactura = new facturaControl(vistaFactura, factura, productodb, personaDB, clienteDB);

    // instancias para el empleado
    empleadoDB modeloEmple = new empleadoDB();
    VistaNuevoEmpleado nuevoEmple = new VistaNuevoEmpleado();
    empleadoControl controlEmple = new empleadoControl(modeloEmple, nuevoEmple);

    //instancias para el crud del empleado
    VistaListaEmple vistaCrudEmple = new VistaListaEmple();
    empleadoCrudControl controlCrudEmple = new empleadoCrudControl(modeloEmple, vistaCrudEmple);

    //isntancias pra el crud del proveedor
    proveedorDB proveedorDB = new proveedorDB();
    RegistroProveedor vistaProveedor = new RegistroProveedor();
    proveedorControl controlProvee = new proveedorControl(proveedorDB, vistaProveedor);

    
     proveedorDB proveedorDB2 = new proveedorDB();
    CrudProveedor vistaCrudProvee = new CrudProveedor();
    crudProveedorControl ctlCrudProve = new crudProveedorControl(proveedorDB2, vistaCrudProvee);
    
    public void iniciarControl() {
        menu.setVisible(true);
        //accion para que inicie el btn de persona ubicado en el menu item
        menu.getBtnNewCustom().addActionListener(e -> btnPersona());
        menu.getJListCustom().addActionListener(e -> listaPersona());
        menu.getBtnListProducts().addActionListener(e -> listaProdcutos());
        menu.getBtnnewInvoice().addActionListener(e -> factura());

        //botones para las opcines del producto
        menu.getBtnNewProduct().addActionListener(e -> btnNuevoProducto());
        menu.getBtnEditPro().addActionListener(e -> listaProdcutosEdit());
        menu.getBtnDeletePro().addActionListener(e -> listaProdcutosDelete());

        //botones para las opciones del empleado
        menu.getBtnNewEmple().addActionListener(e -> btnNuevoEmpleado());

        //botones para las opciones del proveedor 
        menu.getBtnListProveedor().addActionListener(e -> btnNuevoProveedor());
        menu.getBtnListProvider().addActionListener(e -> btnListarProve());
    }

    private void showPanel(JPanel p) {
        p.setSize(1015, 646);
        p.setLocation(0, 0);
        menu.getContent().removeAll();
        menu.getContent().add(p, BorderLayout.CENTER);
        menu.getContent().revalidate();
        menu.getContent().repaint();
    }

    
    public void btnPersona() {

        showPanel(vistaPersona.getPanelIngreso());
        controlPersona.iniciarControl();
    }

    public void listaPersona() {

        showPanel(vista.getjPanel1());
        listaPersona.iniciaControl();
    }

    // --- metodos inicia opciones para el producto
    public void listaProdcutos() {
        showPanel(listaProductos.getPnlContPro());
        listaProductos.getBtnDelete().setVisible(false);
        listaProductos.getBtnNewPro().setVisible(false);
        listaProductos.getBtnEditPro().setVisible(false);
        controlProductos.iniciarControl();
    }

    public void listaProdcutosEdit() {
        listaProductos.getBtnDelete().setVisible(false);
        listaProductos.getBtnNewPro().setVisible(false);
        listaProductos.getBtnEditPro().setVisible(true);
        showPanel(listaProductos.getPnlContPro());
        controlProductos.iniciarControl();
    }

    public void listaProdcutosDelete() {
        listaProductos.getBtnDelete().setVisible(true);
        listaProductos.getBtnNewPro().setVisible(false);
        listaProductos.getBtnEditPro().setVisible(false);
        showPanel(listaProductos.getPnlContPro());
        controlProductos.iniciarControl();
    }

    public void btnNuevoProducto() {
        controRegistrarProduc.iniciarControl();
    }
    // --- metodos finaliza opciones para el prodcuto

    public void factura() {
        controlfactura.iniciarControl();
    }

    //-- metodos para el empleado
    public void btnNuevoEmpleado() {
        controlEmple.iniciarControl();
    }

    public void listaEmpleados() {
        controlCrudEmple.iniciarControl();
    }
    // -- fin metodos para el empleado

    // -- metodos para el proveedor 
    public void btnNuevoProveedor() {
        showPanel(vistaProveedor.getPnlRegisProvedor());
        controlProvee.iniciarControl();
    }

    public void btnListarProve(){
        ctlCrudProve.iniciarControl();
    }
    // fin metodos para el proveedor 
}
