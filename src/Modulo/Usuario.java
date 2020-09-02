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
public class Usuario {

    private int id;
    private String dni;
    private String nombre;
    private String password;
    private String categoria;
    private boolean estado;
    
    public Usuario(){
        
    }
    public Usuario(int id){
        this.id = id;
    }
    public Usuario(int id, String dni, String nombre, String password,String categoria, boolean estado) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.password = password;
        this.categoria=categoria;
        this.estado = estado;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", password=" + password + ", categoria=" + categoria + ", estado=" + estado + '}';
    }
    
    
    
}
