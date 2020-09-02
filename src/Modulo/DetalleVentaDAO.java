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
public class DetalleVentaDAO {
    private PreparedStatement pst=null;
    private ResultSet rs=null;
 //   private String mensaje="";
    
    Conexion conectar = new Conexion();
    
    public void RegisterDetalleVentaDAO(DetalleVenta dv){
        try {
            pst = conectar.consultaPST("INSERT INTO DETALLEVENTA (IDVENTA,IDPRODUCTO,CANTIDAD,PRECIOVENTA)VALUES (?,?,?,?)");
            pst.setInt(1, dv.getIdVenta().getId());
            pst.setInt(2, dv.getIdProducto().getId());
            pst.setInt(3, dv.getCantidad());
            pst.setDouble(4, dv.getPrecioVenta());
            pst.execute();
            pst.close();
        } catch (Exception e) {
            
        }
    }
    
    public List listadoDetalleVentaDAO(){
        List<DetalleVenta> lst = new ArrayList<>();
        try {
            rs = conectar.consultaRS("SELECT * FROM DETALLEVENTA");
            while(rs.next()){
                DetalleVenta dv = new DetalleVenta();
                Ventas v = new Ventas();
                Producto p = new Producto();
                
                dv.setId(rs.getInt(1));
                v.setId(rs.getInt(2));
                dv.setIdVenta(v);
                p.setId(rs.getInt(3));
                dv.setIdProducto(p);
                dv.setCantidad(rs.getInt(4));
                dv.setPrecioVenta(rs.getDouble(5));
                lst.add(dv);
            }
            rs.close();
        } catch (Exception e) {
        }
        return lst;
    }
    
}
