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
public class UsuarioDAO {

    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private String mensaje="";
    
    Conexion conectar = new Conexion();
    
    public Usuario validarLogin(String dni,String pass){
        Usuario user = new Usuario();
        try {
            rs = conectar.consultaRS("SELECT * FROM USUARIO WHERE DNI='"+dni+"' AND PASSWORD='"+pass+"'");
            rs.next();
                user.setId(rs.getInt(1));
                user.setDni(rs.getString(2));
                user.setNombre(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setCategoria(rs.getString(5));
                user.setEstado(rs.getBoolean(6));
            rs.close();
            
        } catch (Exception e) {
            
        }
        return user;
    }
    
    public String registrarUsuarioDAO(Usuario user){
        try {
            pst = conectar.consultaPST("INSERT INTO USUARIO (DNI,NOMBRE,PASSWORD,CATEGORIA,ESTADO) VALUES (?,?,?,?,?)");
            pst.setString(1, user.getDni());
            pst.setString(2, user.getNombre());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getCategoria());
            pst.setBoolean(5, user.isEstado());
            pst.execute();
            mensaje = "SE REGISTRO CON EXITO";
            pst.close();
            
        } catch (Exception e) {
            mensaje = "ERROR AL REGISTRARSE" + e.getMessage();
        }
        return mensaje;
    }
    public String UpdateUsuarioDAO(Usuario user){
        try {
            pst = conectar.consultaPST("UPDATE USUARIO SET DNI=?,NOMBRE=?,PASSWORD=?,CATEGORIA=? WHERE IDUSUARIO=?");
            pst.setString(1, user.getDni());
            pst.setString(2, user.getNombre());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getCategoria());
            pst.setInt(5, user.getId());
            pst.execute();
            mensaje = "SE ACTUALIZO CON EXITO";
            pst.close();
            
        } catch (Exception e) {
            mensaje = "ERROR AL ACTUALIZAR : "+ e.getMessage();
        }
        return mensaje;
    }
    public String DeleteUsuarioDAO(Usuario user){
        try {
            pst = conectar.consultaPST("UPDATE USUARIO SET ESTADO=? WHERE IDUSUARIO=?");
            pst.setBoolean(1, user.isEstado());
            pst.setInt(2, user.getId());
            pst.execute();
            mensaje = "SE ELIMINO CON EXITO";
            pst.close();
        } catch (Exception e) {
            mensaje = "ERROR AL ELIMINAR : "+e.getMessage();
        }
        return mensaje;
    }
    
    public Usuario BuscarUsuarioDAO(int id){
        Usuario user =  new Usuario();
        try {
            rs = conectar.consultaRS("SELECT * FROM USUARIO WHERE IDUSUARIO="+id);
            rs.next();
            user.setId(rs.getInt(1));
            user.setDni(rs.getString(2));
            user.setNombre(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setCategoria(rs.getString(5));
            user.setEstado(rs.getBoolean(6));
            rs.close();
                    
        } catch (Exception e) {
           
        }
        return user;
    }
    public Usuario BuscarUserDniDAO(String dni){
        Usuario user =  new Usuario();
        try {
            rs = conectar.consultaRS("SELECT * FROM USUARIO WHERE DNI='"+dni+"'");
            rs.next();
            user.setId(rs.getInt(1));
            user.setDni(rs.getString(2));
            user.setNombre(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setCategoria(rs.getString(5));
            user.setEstado(rs.getBoolean(6));
            rs.close();
                    
        } catch (Exception e) {
           
        }
        return user;
    }
    
    public List ListadoUsuarioDAO(){
        List<Usuario> lst = new ArrayList<>();
        try {
            rs =  conectar.consultaRS("SELECT * FROM USUARIO WHERE ESTADO=1");
            while(rs.next()){
                Usuario user = new Usuario();
                user.setId(rs.getInt(1));
                user.setDni(rs.getString(2));
                user.setNombre(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setCategoria(rs.getString(5));
                user.setEstado(rs.getBoolean(6));
                lst.add(user);
            }
        } catch (Exception e) {
        }
        return lst;
    }
    
}
