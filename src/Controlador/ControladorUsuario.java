/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modulo.Usuario;
import Modulo.UsuarioDAO;
import Vista.UsuarioForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author mardc
 */
public class ControladorUsuario implements ActionListener {

    UsuarioForm vistaUser = new UsuarioForm();
    Usuario user = new Usuario();
    UsuarioDAO dao = new UsuarioDAO();
    
    
    public ControladorUsuario(UsuarioForm uf){
        this.vistaUser = uf;
        this.vistaUser.btnRegistrar.addActionListener(this);
        this.vistaUser.btnBuscar.addActionListener(this);
        this.vistaUser.btnSalir.addActionListener(this);
        this.vistaUser.btnActualizar.addActionListener(this);
        this.vistaUser.btnEliminar.addActionListener(this);
                
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
         if(ae.getSource() == vistaUser.btnRegistrar){
             RegisterUsuario();
             LimpiarCampos();
         }
         if(ae.getSource()==vistaUser.btnBuscar){
             buscar(vistaUser.txtBuscarDni.getText());
         }
         if(ae.getSource() == vistaUser.btnActualizar){
             UpdateUsuario();
             LimpiarCampos();
         }
         if(ae.getSource() == vistaUser.btnSalir){
             vistaUser.dispose();
         }
         if(ae.getSource() == vistaUser.btnEliminar){
             DeleteUsuario();
             LimpiarCampos();
         }
    }
    
    
    public void LimpiarCampos(){
        vistaUser.txtBuscarDni.setText("");
        vistaUser.txtId.setText("");
        vistaUser.txtDni.setText("");
        vistaUser.txtNombre.setText("");
        vistaUser.txtPassword.setText("");
    }
    public void buscar(String dni){
        if(vistaUser.txtBuscarDni.getText().equals("")){
            JOptionPane.showMessageDialog(vistaUser, "INGRESE EL DNI A BUSCAR");
                    
        }else{
            user = dao.BuscarUserDniDAO(dni);
            if(!vistaUser.txtBuscarDni.getText().equals(user.getDni())){
                JOptionPane.showMessageDialog(vistaUser, "USUARIO NO REGISTRADO");
            }else{
                if(user.isEstado() == false){
                //HACER UNA BUSQUEUUDA SI ESTA REGISTRADO O NO
                JOptionPane.showMessageDialog(vistaUser, "USUARIO DADO DE BAJA");
                LimpiarCampos();
                }else{
                    vistaUser.txtId.setText(""+user.getId());
                    vistaUser.txtDni.setText(user.getDni());
                    vistaUser.txtNombre.setText(user.getNombre());
                    vistaUser.txtPassword.setText(user.getPassword());
                    vistaUser.cbxCategoria.setSelectedItem(user.getCategoria());
                }
            }
        
        }
                
        
    }
    public void DeleteUsuario(){
        if(vistaUser.txtId.getText().equals("")){
            JOptionPane.showMessageDialog(vistaUser, "BUSCAR EL USUARIO A ELIMINAR");
        }else{
            int id = Integer.parseInt(vistaUser.txtId.getText());
            
            user.setId(id);
            user.setEstado(Boolean.FALSE);
            String mensaje = dao.DeleteUsuarioDAO(user);
            JOptionPane.showMessageDialog(vistaUser,mensaje);
        }
    }
    public void UpdateUsuario(){
        if(vistaUser.txtId.getText().equals("") ||
           vistaUser.txtDni.getText().equals("") ||
           vistaUser.txtNombre.getText().equals("") ||
           vistaUser.txtPassword.getText().equals("")){
            
            JOptionPane.showMessageDialog(vistaUser, "FALTA CARGAR DATOS DEL USUARIO");
            vistaUser.txtDni.requestFocus();
        }else{
            int id = Integer.parseInt(vistaUser.txtId.getText());
            String dni = vistaUser.txtDni.getText();
            String nombre = vistaUser.txtNombre.getText();
            String password = vistaUser.txtPassword.getText();
            String categoria = vistaUser.cbxCategoria.getSelectedItem().toString();
            
            user.setId(id);
            user.setDni(dni);
            user.setNombre(nombre);
            user.setPassword(password);
            user.setCategoria(categoria);
            
            String mensaje = dao.UpdateUsuarioDAO(user);
            JOptionPane.showMessageDialog(vistaUser, mensaje);
        }
    }
    public void RegisterUsuario(){
        if(vistaUser.txtDni.getText().equals("") ||
           vistaUser.txtNombre.getText().equals("") ||
           vistaUser.txtPassword.getText().equals("")){
            
            JOptionPane.showMessageDialog(vistaUser, "COMPLETAR LOS CAMPOS");
            vistaUser.txtDni.requestFocus();
        }else{
            String dni = vistaUser.txtDni.getText();
            String nombre = vistaUser.txtNombre.getText();
            String password = vistaUser.txtPassword.getText();
            String categoria = vistaUser.cbxCategoria.getSelectedItem().toString();
            
            user.setDni(dni);
            user.setNombre(nombre);
            user.setPassword(password);
            user.setCategoria(categoria);
            user.setEstado(Boolean.TRUE);
            
            String mensaje = dao.registrarUsuarioDAO(user);
            JOptionPane.showMessageDialog(vistaUser, mensaje);
        }
    }
    
}
