/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mardc
 */
public class ProductoDAO {
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private String mensaje="";
    
    Conexion conectar = new Conexion();

    public String RegisterProductoDAO(Producto p){
        try {
            pst = conectar.consultaPST("INSERT INTO PRODUCTO (NOMBRE,CATEGORIA,PRECIO,STOCK,ESTADO)VALUES (?,?,?,?,?)");
            pst.setString(1, p.getNombre());
            pst.setString(2, p.getCategoria());
            pst.setDouble(3, p.getPrecio());
            pst.setInt(4, p.getStock());
            pst.setBoolean(5,p.isEstado());
            pst.execute();
            mensaje = "SE REGISTRO CON EXITO";
            pst.close();
        } catch (Exception e) {
            mensaje = "ERROR AL REGISTRAR "+ e.getMessage();
        }
        return mensaje;
    }
    public String UpdateProductoDAO(Producto p){
        try {
            //NO SE ACTUALIZA CATEGORIA
            pst = conectar.consultaPST("UPDATE PRODUCTO SET NOMBRE=?,PRECIO=?,STOCK=? WHERE IDPRODUCTO=?");
            pst.setString(1, p.getNombre());
            pst.setDouble(2, p.getPrecio());
            pst.setInt(3, p.getStock());
            pst.setInt(4, p.getId());
            pst.execute();
            mensaje = "SE ACTUALIZO CON EXITO";
            pst.close();
        } catch (Exception e) {
            mensaje = "ERROR AL ACTUALIZAR :"+ e.getMessage();
        }
        return mensaje;
    }
    public String DeleteProductoDAO(Producto p){
        try {
            pst = conectar.consultaPST("UPDATE PRODUCTO SET ESTADO=? WHERE IDPRODUCTO=? ");
            pst.setBoolean(1, p.isEstado());
            pst.setInt(2, p.getId());
            pst.execute();
            mensaje  = "SE ELIMINO CON EXITO";
            pst.close();
        } catch (Exception e) {
            mensaje = "ERROR AL ELIMINAR "+ e.getMessage();
        }
        return mensaje;
    }
    public Producto BuscarProductoDAO(int id){
        Producto p = new Producto();
        try {
          rs = conectar.consultaRS("SELECT * FROM PRODUCTO WHERE IDPRODUCTO="+id);
          rs.next();
          p.setId(rs.getInt(1));
          p.setNombre(rs.getString(2));
          p.setCategoria(rs.getString(3));
          p.setPrecio(rs.getDouble(4));
          p.setStock(rs.getInt(5));
          p.setEstado(rs.getBoolean(6));
          rs.close();
          
        } catch (Exception e) {
        }
        return p;
    }
    public List ListadoProductoDAO(){
        List<Producto> lst = new ArrayList<>();
        try {
            rs = conectar.consultaRS("SELECT * FROM PRODUCTO WHERE ESTADO=1");
            while(rs.next()){
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCategoria(rs.getString(3));
                p.setPrecio(rs.getDouble(4));
                p.setStock(rs.getInt(5));
                p.setEstado(rs.getBoolean(6));
                lst.add(p);
                        
            }
            rs.close();
            
        } catch (Exception e) {
        }
        return lst;
    }
    
}


