/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import java.sql.Date;

/**
 *
 * @author mardc
 */
public class Ventas {
        private int id;
        private Cliente idCliente;
        private Usuario idUsuario;
        private String numeroVenta;
        private Date fechaVenta;
        private Double monto;
        private boolean estado;
        
    public Ventas(){
        
        }

    public Ventas(int id, Cliente idCliente, Usuario idUsuario, String numeroVenta, Date fechaVenta, Double monto, boolean estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.numeroVenta = numeroVenta;
        this.fechaVenta = fechaVenta;
        this.monto = monto;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
                
        
        
}
