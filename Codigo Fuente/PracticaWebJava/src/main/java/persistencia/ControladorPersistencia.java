/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.producto;
import logica.productoproveedor;
import logica.proveedor;
import logica.tipoproducto;
import persistencia.exceptions.NonexistentEntityException;

public class ControladorPersistencia {
    productoJpaController prodJpa = new productoJpaController();
    tipoproductoJpaController tipoJpa = new tipoproductoJpaController();
    productoproveedorJpaController prodproveeJpa = new productoproveedorJpaController();
    proveedorJpaController proveeJpa = new proveedorJpaController();
    
    public void crearProducto(producto prod){
        prodJpa.create(prod);
    }
    public List<producto> traerProductos (){
        return prodJpa.findproductoEntities();
    }

    public void borrarProducto(int id_eliminar) {
        try {
            prodJpa.destroy(id_eliminar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public producto traerProducto(int id_editar) {
        return prodJpa.findproducto(id_editar);
    }

    public void editarProducto(producto prod) {
        try {
            prodJpa.edit(prod);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<producto> buscarProductos(String claveProducto, String tipoProducto) {
        return prodJpa.findProductosByParams(claveProducto, tipoProducto);
    }

    public List<tipoproducto> traerTipos() {
        return tipoJpa.findtipoproductoEntities();
    }

    public List<productoproveedor> traerProveedores(int id_editar) {
        return prodproveeJpa.findProductosByParams(String.valueOf(id_editar));
    }

    public void borrarProductoProovedor(int id_eliminar) {
        try {
            prodproveeJpa.destroy(id_eliminar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearProductoProveedor(productoproveedor prodprovee) {
        prodproveeJpa.create(prodprovee);
    }

    public List<proveedor> traerProveedores() {
        return proveeJpa.findproveedorEntities();
    }

    public productoproveedor traerProveedor(int idProductoProveedor) {
        return prodproveeJpa.findproductoproveedor(idProductoProveedor);
    }

    public void editProdutoProveedor(productoproveedor prodprovee) {
        try {
            prodproveeJpa.edit(prodprovee);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
