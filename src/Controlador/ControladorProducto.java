 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modulo.Producto;
import Modulo.ProductoDAO;
import Vista.ProductoForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author mardc
 */
public class ControladorProducto implements ActionListener{

    Producto producto = new Producto();
    ProductoDAO dao = new ProductoDAO();
    ProductoForm vistaProducto =  new ProductoForm();
    
    
    public ControladorProducto(ProductoForm pf){
       this.vistaProducto = pf;
       this.vistaProducto.btnRegistrar.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==vistaProducto.btnRegistrar){
            RegisterProducto();
            limpiarCampos();
            
        }
    }
    public void limpiarCampos(){
        vistaProducto.txtId.setText("");
        vistaProducto.txtNombre.setText("");
        vistaProducto.spnStock.setValue(0);
        vistaProducto.txtprecio.setText("");
        vistaProducto.cbxCategoria.setSelectedIndex(0);
                
        
    }
    public void RegisterProducto(){
        if(vistaProducto.txtNombre.getText().equals("") ||
           vistaProducto.txtprecio.getText().equals("")){
            JOptionPane.showMessageDialog(vistaProducto, "COMPLETAR LOS CAMPOS");
            vistaProducto.txtNombre.requestFocus();
        }else{
            String nombre =  vistaProducto.txtNombre.getText();
            int stock = Integer.parseInt(vistaProducto.spnStock.getValue().toString());
            double precio = Double.parseDouble(vistaProducto.txtprecio.getText());
            String categoria  = vistaProducto.cbxCategoria.getSelectedItem().toString();
            
            producto.setNombre(nombre);
            producto.setStock(stock);
            producto.setPrecio(precio);
            producto.setCategoria(categoria);
            producto.setEstado(Boolean.TRUE);
            
            String mensaje =  dao.RegisterProductoDAO(producto);
            JOptionPane.showMessageDialog(vistaProducto, mensaje);
            
        }
    }
    
}
