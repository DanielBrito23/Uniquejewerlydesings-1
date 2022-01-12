/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.modelo;

/**
 *
 * @author corin
 */
public class persona {
  private int id_persona;
  private String cedula;
  private String nombres;
  private String direccion;
  private String telefono;
  private String correo;

    public persona() {
    }

    public persona(int id_persona, String cedula, String nombres, String direccion, String telefono, String correo) {
        this.id_persona = id_persona;
        this.cedula = cedula;
        this.nombres = nombres;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public persona(int id_persona, String nombres) {
        this.id_persona = id_persona;
        this.nombres = nombres;
    }
  
    
    
    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Persona{" + "id_persona=" + id_persona + ", cedula=" + cedula + ", nombres=" + nombres + ", direccion=" + direccion + ", telefono=" + telefono + ", correo=" + correo + '}';
    }

   
    

   }
