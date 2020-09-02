/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modulo.Empleado;
import Modulo.EmpleadoDAO;
import Vista.EmpleadoForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mardc
 */
public class ControladorEmpleado  implements ActionListener{

    Empleado emp = new Empleado();
    EmpleadoDAO dao = new EmpleadoDAO();
    EmpleadoForm vistaEmpleado = new EmpleadoForm();
    DefaultTableModel model = new DefaultTableModel();
    
    public ControladorEmpleado(EmpleadoForm emp){
          this.vistaEmpleado = emp;
          this.vistaEmpleado.btnGuardar.addActionListener(this);
          this.vistaEmpleado.btnListar.addActionListener(this);
          this.vistaEmpleado.btnEditar.addActionListener(this);
          this.vistaEmpleado.btnActualizar.addActionListener(this);
          this.vistaEmpleado.btnEliminar.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==vistaEmpleado.btnGuardar){
            limpiarTabla();
            registrarEmpleado();
            ListarEmpleado(vistaEmpleado.table);
        }
        if(ae.getSource()==vistaEmpleado.btnListar){
            limpiarTabla();
            ListarEmpleado(vistaEmpleado.table);
        }
        if(ae.getSource()==vistaEmpleado.btnEditar){
            int fila = vistaEmpleado.table.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(vistaEmpleado, "DEBE DE SELECCIONAR UNA FILA");
            }else{
                int id = Integer.parseInt((String)vistaEmpleado.table.getValueAt(fila, 0).toString());
                String nombre = (String)vistaEmpleado.table.getValueAt(fila, 1);
                String email = (String)vistaEmpleado.table.getValueAt(fila, 2);
                String telefono = (String)vistaEmpleado.table.getValueAt(fila, 3);
                String pasword = (String)vistaEmpleado.table.getValueAt(fila, 4);
                vistaEmpleado.txtd.setText(""+id);
                vistaEmpleado.txtNombre.setText(nombre);
                vistaEmpleado.txtEmail.setText(email);
                vistaEmpleado.txtTelefono.setText(telefono);
                vistaEmpleado.txtPassword.setText(pasword);
                     
           }
        }
        if(ae.getSource()==vistaEmpleado.btnActualizar){
            ActualizarEmpleado();
            limpiarTabla();
            limpiarCampos();
            ListarEmpleado(vistaEmpleado.table);
        }
        if(ae.getSource()==vistaEmpleado.btnEliminar){
            int fila = vistaEmpleado.table.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(vistaEmpleado, "SELECCIONE UNA FILA");
            }else{
                int id =Integer.parseInt((String)vistaEmpleado.table.getValueAt(fila, 0).toString());
               
                emp.setEstado(Boolean.FALSE);
                emp.setId(id);
                EliminarDAO(emp);
                limpiarTabla();
                limpiarCampos();
                ListarEmpleado(vistaEmpleado.table);
            }
        }
    }
    
    public void limpiarCampos(){
            vistaEmpleado.txtd.setText("");
            vistaEmpleado.txtNombre.setText("");
            vistaEmpleado.txtEmail.setText("");
            vistaEmpleado.txtTelefono.setText("");
            vistaEmpleado.txtPassword.setText("");
    }
    public void limpiarTabla(){
        
        for (int i = 0; i < vistaEmpleado.table.getRowCount(); i++) {
            model.removeRow(i);
            i=i-1;
        }
    }
    
    public void ListarEmpleado(JTable tabla){
    
        model = (DefaultTableModel)tabla.getModel();
        
        List<Empleado> lst = dao.ListadoEmpleadoDAO();
        Object[] objeto =  new Object[5];
        
        for (int i = 0; i < lst.size(); i++) {
            objeto[0] = lst.get(i).getId();
            objeto[1]=lst.get(i).getNombre();
            objeto[2]=lst.get(i).getEmail();
            objeto[3]=lst.get(i).getTelefono();
            objeto[4]=lst.get(i).getPassword();
            model.addRow(objeto);
        }
      
        vistaEmpleado.table.setModel(model);
    }
    public void EliminarDAO(Empleado emp){
      String  mensaje = dao.EliminarDAO(emp);
      JOptionPane.showMessageDialog(vistaEmpleado, mensaje);
    }
    
    public void ActualizarEmpleado(){
        if(vistaEmpleado.txtNombre.getText().equals("") ||
                vistaEmpleado.txtEmail.getText().equals("")||
                vistaEmpleado.txtTelefono.getText().equals("")||
                vistaEmpleado.txtPassword.getText().equals("")){
            JOptionPane.showMessageDialog(vistaEmpleado, "FORMULARIO INCOMPLETO");
        }else{
            int id = Integer.parseInt(vistaEmpleado.txtd.getText());
            String nombre = vistaEmpleado.txtNombre.getText();
            String email = vistaEmpleado.txtEmail.getText();
            String telefono =vistaEmpleado.txtTelefono.getText();
            String password = vistaEmpleado.txtPassword.getText();
                emp.setId(id);
                emp.setNombre(nombre);
                emp.setEmail(email);
                emp.setTelefono(telefono);
                emp.setPassword(password);
            String mensaje  = dao.ActualizarDAO(emp);
            JOptionPane.showMessageDialog(vistaEmpleado, mensaje);
                
        }
    }
    public void registrarEmpleado(){
         if(vistaEmpleado.txtNombre.getText().equals("") || 
                 vistaEmpleado.txtEmail.getText().equals("") ||
                 vistaEmpleado.txtTelefono.getText().equals("") ||
                 vistaEmpleado.txtPassword.getText().equals("")){
               JOptionPane.showMessageDialog(vistaEmpleado, "FORMULARIO INCOMPLETO");
               vistaEmpleado.txtNombre.requestFocus();
            }else{
                String nombre = vistaEmpleado.txtNombre.getText();
                String email = vistaEmpleado.txtEmail.getText();
                String telefono =vistaEmpleado.txtTelefono.getText();
                String password = vistaEmpleado.txtPassword.getText();
                
                emp.setNombre(nombre);
                emp.setEmail(email);
                emp.setTelefono(telefono);
                emp.setPassword(password);
                emp.setEstado(Boolean.TRUE);
                
                String mensaje =  dao.RegistrarDAO(emp);
                JOptionPane.showMessageDialog(vistaEmpleado, mensaje);
                
                        
         }
     }
    
    
}
