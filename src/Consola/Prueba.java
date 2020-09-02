/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consola;

import Modulo.Empleado;
import Modulo.EmpleadoDAO;
import Modulo.Usuario;
import Modulo.UsuarioDAO;


/**
 *
 * @author mardc
 */
public class Prueba {
        public static void main(String args[]) {
            
//            Empleado emp = new Empleado();
//            EmpleadoDAO dao = new EmpleadoDAO();
//            
//            emp = dao.validarLogin("12345", "12345");
//            
//            System.out.println(emp.toString());
                Usuario user = new Usuario();
                UsuarioDAO dao  = new UsuarioDAO(); 
        
                user = dao.validarLogin("12345", "567");
                
                System.out.println(user.toString());
                
                
                
                
                
        }

        
}
