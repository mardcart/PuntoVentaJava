/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

/**
 *
 * @author mardc
 */
public class Cliente {
    private int id;
    private String dni;
    private String nombre;
    private String email;
    private boolean estado;

    public Cliente(int id, String dni, String nombre, String email, boolean estado) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
    }
    
    public Cliente(){
        
    }
    public Cliente(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", email=" + email + ", estado=" + estado + '}';
    }
                
}
