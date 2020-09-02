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
public class ClienteDAO {

    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private String mensaje="";
    
    Conexion conectar = new Conexion();
    
    public String RegisterClienteDAO(Cliente cli){
        try {
            pst = conectar.consultaPST("INSERT INTO CLIENTE (DNI,NOMBRE,EMAIL,ESTADO)VALUES (?,?,?,?)");
            pst.setString(1, cli.getDni());
            pst.setString(2, cli.getNombre());
            pst.setString(3, cli.getEmail());
            pst.setBoolean(4, cli.isEstado());
            pst.execute();
            mensaje = "SE REGISTRO CON EXITO";
            pst.close();
        } catch (Exception e) {
            mensaje = "ERROR AL REGISTRAR "+ e.getMessage();
        }
        return mensaje;
    }
    public String UpdateClienteDAO(Cliente cli){
        try {
            pst = conectar.consultaPST("UPDATE CLIENTE SET DNI=?,NOMBRE=?,EMAIL=? WHERE IDCLIENTE=?");
            pst.setString(1, cli.getDni());
            pst.setString(2, cli.getNombre());
            pst.setString(3, cli.getEmail());
            pst.setInt(4, cli.getId());
            pst.execute();
            mensaje = "SE ACTUALIZO CON EXITO";
            pst.close();
        } catch (Exception e) {
            mensaje = "ERROR AL ACTUALIZAR "+e.getMessage();
        }
        return mensaje;
    }
    public String DeleteClienteDAO(Cliente cli){
        try {
            pst = conectar.consultaPST("UPDATE CLIENTE SET ESTADO=? WHERE IDCLIENTE=?");
            pst.setBoolean(1, cli.isEstado());
            pst.setInt(2, cli.getId());
            mensaje = "SE ELIMINO CON EXITO";
        } catch (Exception e) {
            mensaje = "ERRO AL ELIMINAR "+ e.getMessage();
        }
        return mensaje;
    }
    public Cliente BuscarClienteDAO(String dni){
        Cliente c = new Cliente();
        try {
            rs = conectar.consultaRS("SELECT * FROM CLIENTE WHERE DNI='"+dni+"'");
            rs.next();
            c.setId(rs.getInt(1));
            c.setDni(rs.getString(2));
            c.setNombre(rs.getString(3));
            c.setEmail(rs.getString(4));
            c.setEstado(rs.getBoolean(5));
            rs.close();
            } catch (Exception e) {
        }
        return c;
    }
    public List ListadoClienteDAO(){
        List<Cliente> lst = new ArrayList<>();
        try {
            rs = conectar.consultaRS("SELECT * FROM CLIENTE WHERE ESTADO=1");
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setEstado(rs.getBoolean(5));
                lst.add(c);
                
            }
            //si no funciona es por el rs.close();
            rs.close();
                   
        } catch (Exception e) {
        }
        return lst;
    }
    
}
