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
public class VentasDAO {
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private String mensaje="";
    
    Conexion conectar = new Conexion();
    
    public String RegisterVentaDAO(Ventas v){
        try {
            pst = conectar.consultaPST("INSERT INTO VENTA (IDCLIENTE,IDUSUARIO,NUMEROVENTA,FECHA,MONTO,ESTADO)values (?,?,?,?,?,?)");
            pst.setInt(1, v.getIdCliente().getId());
            pst.setInt(2, v.getIdUsuario().getId());
            pst.setString(3, v.getNumeroVenta());
            pst.setDate(4, v.getFechaVenta());
            pst.setDouble(5, v.getMonto());
            pst.setBoolean(6, v.isEstado());
            pst.execute();
            mensaje = "SE REGISTRO LA VENTA CON EXITO";
            pst.close();
            
        } catch (Exception e) {
            mensaje = "ERROR AL GENERAR LA VENTA "+e.getMessage();
        }
        
        return mensaje;
    }
    public Ventas BuscarVenta(String nroVenta){
        Ventas v = new Ventas();
        Cliente c = new Cliente();
        Usuario u = new Usuario();
        try {
            rs = conectar.consultaRS("SELECT * FROM VENTA WHERE NUMEROVENTA="+nroVenta);
            rs.next();        
                v.setId(rs.getInt(1));
                c.setId(rs.getInt(2));
                v.setIdCliente(c);
                u.setId(rs.getInt(3));
                v.setIdUsuario(u);
                v.setNumeroVenta(rs.getString(4));
                v.setMonto(rs.getDouble(5));
                v.setEstado(rs.getBoolean(6));
            rs.close();
            
        } catch (Exception e) {
        }
        return v;
    }
    public List ListadoVentasDAO(){
        List<Ventas> lst = new ArrayList<>();
        try {
            rs = conectar.consultaRS("SELECT * FROM VENTA");
            while(rs.next()){
                Ventas v = new Ventas();
                Cliente c = new Cliente();
                Usuario u = new Usuario();
                
                v.setId(rs.getInt(1));
                c.setId(rs.getInt(2));
                v.setIdCliente(c);
                u.setId(rs.getInt(3));
                v.setIdUsuario(u);
                v.setNumeroVenta(rs.getString(4));
                v.setMonto(rs.getDouble(5));
                v.setEstado(rs.getBoolean(6));
                lst.add(v);
                        
            }
            rs.close();
        } catch (Exception e) {
        }
        return lst;
    }
    
}












