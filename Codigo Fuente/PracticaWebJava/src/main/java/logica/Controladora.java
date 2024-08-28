/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import persistencia.ControladorPersistencia;

public class Controladora {
    ControladorPersistencia controlPersis = new ControladorPersistencia();
    
    public void crearProducto (producto prod){
        controlPersis.crearProducto(prod);
    }
    
    public List<producto> traerProductos (){
        return controlPersis.traerProductos();
    }
    
    public List<producto> buscarProductos (String claveProducto, String tipoProducto){
        return controlPersis.buscarProductos(claveProducto, tipoProducto);
    }

    public void borrarProducto(int id_eliminar) {
        controlPersis.borrarProducto(id_eliminar);
    }

    public producto traerProducto(int id_editar) {
        return controlPersis.traerProducto(id_editar);
    }

    public void editarProducto(producto prod) {
        controlPersis.editarProducto(prod);
    }
    
    public List<tipoproducto> traerTipos (){
        return controlPersis.traerTipos();
    }

    public List<productoproveedor> traerProveedores(int id_editar) {
        return controlPersis.traerProveedores(id_editar);
    }

    public void borrarProductoProovedor(int id_eliminar) {
        controlPersis.borrarProductoProovedor(id_eliminar);
    }

    public void crearProductoProveedor(productoproveedor prodprovee) {
        controlPersis.crearProductoProveedor(prodprovee);
    }
    
    public List<proveedor> traerProveedores (){
        return controlPersis.traerProveedores();
    }

    public productoproveedor traerProveedor(int idProductoProveedor) {
        return controlPersis.traerProveedor(idProductoProveedor);
    }

    public void editProdutoProveedor(productoproveedor prodprovee) {
        controlPersis.editProdutoProveedor(prodprovee);
    }
}
