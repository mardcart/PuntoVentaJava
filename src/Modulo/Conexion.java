/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * SE CREA LA CLASE CONEXION PARA BD CON DOS METODOS
 * UNOS PST Y RS
 * @author mardc
 */
public class Conexion {
        private static final String CLASE="com.mysql.jdbc.Driver";
    
    private final String database = "bd_sistemaventas";
    private final String hostname = "localhost";
    private final String port = "3306";
    private final String horario = "?serverTimezone=UTC";
    private final String url = "jdbc:mysql://"+hostname+":"+port+"/"+database+horario;
    private final String user= "root";
    private final String pass= "root";
  
    private  Statement st = null;
    private  PreparedStatement pst = null;
    private  ResultSet rs = null;
  
    private Connection cn;
    
    public Connection getConexion(){
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            
        }
        
        return cn;
    }
    
    public PreparedStatement consultaPST(String consulta){
        try {
            cn = getConexion();
            pst = cn.prepareStatement(consulta);
        } catch (Exception e) {
        }
        return pst;
    }
    public ResultSet consultaRS(String consulta){
        try {
            cn = getConexion();
            st = cn.createStatement();
            rs = st.executeQuery(consulta);
        } catch (Exception e) {
            
        }
        return rs;
    }

}
