/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modulo.Empleado;
import Modulo.EmpleadoDAO;
import Modulo.Usuario;
import Modulo.UsuarioDAO;
import Vista.Login;
import Vista.MenuJR;
import Vista.MenuSR;
//import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author mardc
 */
public class ControladorUser implements ActionListener{

    Empleado emp = new Empleado();
    EmpleadoDAO dao = new EmpleadoDAO();
    Usuario user = new Usuario();
    UsuarioDAO usDAO = new UsuarioDAO();
    Login login = new Login();
    
    public ControladorUser(Login l){
        this.login = l;
        this.login.btnIniciar.addActionListener(this);
    }
    public void validar(){
        String dni = login.txtUser.getText();
        String  pass =  login.txtPass.getText();
        if(login.txtUser.getText().equals("") || login.txtPass.getText().equals("")){
            JOptionPane.showMessageDialog(login, "INGRESAR DATOS");
            login.txtUser.requestFocus();
        }else{

            user = usDAO.validarLogin(dni, pass);
            if(dni.equals(user.getDni()) && pass.equals(user.getPassword())){
                String categ = user.getCategoria();
                switch(categ){
                    case "JUNIOR":
                        MenuJR jr =  new MenuJR();
                        jr.setVisible(true);
                        jr.setLocationRelativeTo(null);
                        login.dispose();
                        
                        break;
                    case "SEMI":
                        break;
                    case "SENIOR":
//                        Principal p = new Principal();
//                        p.setVisible(true);
//                        p.setLocationRelativeTo(null);
//                        login.dispose();
                          MenuSR sr = new MenuSR();
                          sr.setVisible(true);
                          sr.setLocationRelativeTo(null);
                          login.dispose();
                        break;
                    default:
                        break;
                        
                    
                }
            }else{
                  JOptionPane.showMessageDialog(login, "DATOS INCORRECTOS");
                   login.txtUser.requestFocus();
            }
//            emp = dao.validarLogin(telefono, pass);
//            if(emp.getTelefono() !=null && emp.getPassword() !=null){
//                Principal p = new Principal();
//                p.setVisible(true);
//                p.setLocationRelativeTo(null);
//                login.dispose();
//                        
//                
//            }else{
//                JOptionPane.showMessageDialog(login, "DATOS INCORRECTOS");
//                login.txtUser.requestFocus();
//            }
        }
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == login.btnIniciar){
            validar();
        }
    }
    
}







