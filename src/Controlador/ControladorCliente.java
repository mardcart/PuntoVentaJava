/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modulo.Cliente;
import Modulo.ClienteDAO;
import Vista.ClienteForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author mardc
 */
public class ControladorCliente implements ActionListener {
    
    ClienteForm vistaCliente = new ClienteForm();
    Cliente cliente = new Cliente();
    ClienteDAO dao = new ClienteDAO();
    
    public ControladorCliente(ClienteForm cf){
       this.vistaCliente = cf;
       this.vistaCliente.btnRegistrar.addActionListener(this);
       this.vistaCliente.btnSalir.addActionListener(this);
       this.vistaCliente.btnBuscar.addActionListener(this);
       this.vistaCliente.btnActualizar.addActionListener(this);
       this.vistaCliente.btnEliminar.addActionListener(this);
               
               
               
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        //ACA VAN EL LLAMADO A LOS BOTONES GETSOURCE COMPARA
        if(ae.getSource() == vistaCliente.btnRegistrar){
            RegisterCliente();
            LimpiarCampos();
        }
        if(ae.getSource() ==  vistaCliente.btnSalir){
            vistaCliente.dispose();
        }
        if(ae.getSource() == vistaCliente.btnBuscar){
            BuscarDniCliente();
                    
        }
        if(ae.getSource() == vistaCliente.btnActualizar){
            UpdateCliente();
            LimpiarCampos();
        }
        if(ae.getSource() == vistaCliente.btnEliminar){
            DeleteCliente();
            LimpiarCampos();
        }
    }
    public void LimpiarCampos(){
        vistaCliente.txtId.setText("");
        vistaCliente.txtDni.setText("");
        vistaCliente.txtNombre.setText("");
        vistaCliente.txtEmail.setText("");
    }
    public void BuscarDniCliente(){
        if(vistaCliente.txtBuscarDniC.getText().equals("")){
            JOptionPane.showMessageDialog(vistaCliente, "INGRESAR DNI A BUSCAR");              
        }else{
            cliente = dao.BuscarClienteDAO(vistaCliente.txtBuscarDniC.getText());
            if(vistaCliente.txtBuscarDniC.getText().equals(cliente.getDni())){
                if(cliente.isEstado()==Boolean.TRUE){
                   vistaCliente.txtId.setText(""+cliente.getId());
                   vistaCliente.txtDni.setText(cliente.getDni());
                   vistaCliente.txtNombre.setText(cliente.getNombre());
                   vistaCliente.txtEmail.setText(cliente.getEmail());
                }else{
                    JOptionPane.showMessageDialog(vistaCliente, "CLIENTE DADO DE BAJA");
                    LimpiarCampos();
                }
            }else{
                JOptionPane.showMessageDialog(vistaCliente, "CLIENTE NO REGISTRADO");
            }
            
        }
    }
    public void DeleteCliente(){
        if(vistaCliente.txtId.getText().equals("")){
            JOptionPane.showMessageDialog(vistaCliente, "BUSCAR EL CLIENTE");
            vistaCliente.txtBuscarDniC.requestFocus();
        }else{
            int id = Integer.parseInt(vistaCliente.txtId.getText());
            
            cliente.setId(id);
            cliente.setEstado(Boolean.FALSE);
            String mensaje = dao.DeleteClienteDAO(cliente);
            JOptionPane.showMessageDialog(vistaCliente, mensaje);
                    
        }
    }
        
    public void UpdateCliente(){
        if(vistaCliente.txtId.getText().equals("") ||
          vistaCliente.txtDni.getText().equals("") ||
          vistaCliente.txtNombre.getText().equals("") ||
          vistaCliente.txtEmail.getText().equals("")){
           JOptionPane.showMessageDialog(vistaCliente, "COMPLETAR TODOS LOS CAMPOS");
           vistaCliente.txtDni.requestFocus();
       }else{
            int id =  Integer.parseInt(vistaCliente.txtId.getText());
           String dni =  vistaCliente.txtDni.getText();
           String nombre = vistaCliente.txtNombre.getText();
           String email = vistaCliente.txtEmail.getText();
           
           cliente.setId(id);
           cliente.setDni(dni);
           cliente.setNombre(nombre);
           cliente.setEmail(email);
           cliente.setEstado(Boolean.TRUE);
           
           String mensaje = dao.UpdateClienteDAO(cliente);
           JOptionPane.showMessageDialog(vistaCliente, mensaje);
       }
    }
    public void RegisterCliente(){
       if(vistaCliente.txtDni.getText().equals("") ||
          vistaCliente.txtNombre.getText().equals("") ||
          vistaCliente.txtEmail.getText().equals("")){
           JOptionPane.showMessageDialog(vistaCliente, "COMPLETAR TODOS LOS CAMPOS");
           
       }else{
           String dni =  vistaCliente.txtDni.getText();
           String nombre = vistaCliente.txtNombre.getText();
           String email = vistaCliente.txtEmail.getText();
           
           cliente.setDni(dni);
           cliente.setNombre(nombre);
           cliente.setEmail(email);
           cliente.setEstado(Boolean.TRUE);
           
           String mensaje = dao.RegisterClienteDAO(cliente);
           JOptionPane.showMessageDialog(vistaCliente, mensaje);
       }
    }
}






