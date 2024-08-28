/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author DiegoBP
 */
@Entity
public class productoproveedor implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int IdProductoProveedor;
    private int IdProducto;
    private int IdProveedor;
    private String ClaveProductoProveedor;
    private String Costo;

    public productoproveedor() {
    }

    public productoproveedor(int IdProductoProveedor, int IdProducto, int IdProveedor, String ClaveProductoProveedor, String Costo) {
        this.IdProductoProveedor = IdProductoProveedor;
        this.IdProducto = IdProducto;
        this.IdProveedor = IdProveedor;
        this.ClaveProductoProveedor = ClaveProductoProveedor;
        this.Costo = Costo;
    }

    public int getIdProductoProveedor() {
        return IdProductoProveedor;
    }

    public void setIdProductoProveedor(int IdProductoProveedor) {
        this.IdProductoProveedor = IdProductoProveedor;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public String getClaveProductoProveedor() {
        return ClaveProductoProveedor;
    }

    public void setClaveProductoProveedor(String ClaveProductoProveedor) {
        this.ClaveProductoProveedor = ClaveProductoProveedor;
    }

    public String getCosto() {
        return Costo;
    }

    public void setCosto(String Costo) {
        this.Costo = Costo;
    }
}
