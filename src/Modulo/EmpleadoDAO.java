/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mardc
 */
public class EmpleadoDAO {
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private String mensaje = "";

    Conexion conectar = new Conexion();
    
    
    
    
    public String RegistrarDAO(Empleado emp){
        try {
            pst = conectar.consultaPST("INSERT INTO EMPLEADO (NOMBRE,EMAIL,TELEFONO,PASSWORD,ESTADO)VALUES(?,?,?,?,?)");
            pst.setString(1, emp.getNombre());
            pst.setString(2, emp.getEmail());
            pst.setString(3, emp.getTelefono());
            pst.setString(4, emp.getPassword());
            pst.setBoolean(5, emp.getEstado());
            pst.execute();
            mensaje = "SE REGISTRO CON EXITO";
            pst.close();
        } catch (Exception e) {
            mensaje = "ERROR AL REGISTRAR"+e.getMessage();
        }
        return mensaje;
    }
    public String EliminarDAO(Empleado emp){
        try {
            pst = conectar.consultaPST("UPDATE EMPLEADO SET estado=? WHERE idempleado=?");
            pst.setBoolean(1, emp.getEstado());
            pst.setInt(2, emp.getId());
            pst.execute();
            mensaje = "SE ELIMINO CON EXITO ";
            pst.close();
        } catch (Exception e) {
            mensaje = "NO SE PUEDE ELIMINAR " + e.getMessage();
        }
        return mensaje;
    }
    
    public Empleado BuscarDAO(int id){
        Empleado emp = new Empleado();
        try {
            rs = conectar.consultaRS("select * from empleado where idempleado="+id);
            rs.next();
            emp.setId(rs.getInt(1));
            emp.setNombre(rs.getString(2));
            emp.setEmail(rs.getString(3));
            emp.setTelefono(rs.getString(4));
            emp.setPassword(rs.getString(5));
            emp.setEstado(rs.getBoolean(6));
            rs.close();
                   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
    
    public String ActualizarDAO(Empleado e){
        try {
            pst = conectar.consultaPST("UPDATE EMPLEADO SET nombre=?,email=?,telefono=?,password=? where idempleado=?");
            
            pst.setString(1, e.getNombre());
            pst.setString(2, e.getEmail());
            pst.setString(3, e.getTelefono());
            pst.setString(4, e.getPassword());
            //pst.setBoolean(5, e.getEstado());
            pst.setInt(5, e.getId());
            pst.execute();
            
            mensaje = "SE ACTUALIZO CON EXITO";
            
            pst.close();
            
        } catch (Exception x) {
            mensaje = "ERROR AL AACTUALIZAR"+x.getMessage();
        }
        return mensaje;
    }
    
    public Empleado validarLogin(String telefono,String pass){
        Empleado emp = new Empleado();
        String consulta = "select * from empleado where telefono="+telefono+" and password="+pass+"";
        try {
            rs = conectar.consultaRS(consulta);
            if(rs.next()){
                emp.setId(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setEmail(rs.getString(3));
                emp.setTelefono(rs.getString(4));
                emp.setPassword(rs.getString(5));
                emp.setEstado(rs.getBoolean(6));
                rs.close();

            }
                        
        } catch (Exception e) {
        }
        
        return emp;
    }
    public List ListadoEmpleadoDAO(){
        List<Empleado> lst = new ArrayList<>();
        try {
            rs = conectar.consultaRS("select * from empleado WHERE ESTADO=1");
            while(rs.next()){
                Empleado e = new Empleado();
                e.setId(rs.getInt(1));
                e.setNombre(rs.getString(2));
                e.setEmail(rs.getString(3));
                e.setTelefono(rs.getString(4));
                e.setPassword(rs.getString(5));
                e.setEstado(rs.getBoolean(6));
                lst.add(e);
                       
            }
        } catch (Exception e) {
         
        }
        return lst;
    }
    
}








