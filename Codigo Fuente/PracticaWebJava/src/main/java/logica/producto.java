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
public class producto implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int IdProducto;
    private String claveProducto;
    private String nombre;
    private String precio;
    private String IdTipoProducto;

    public producto() {
    }

    public producto(int id, String claveProducto, String nombre, String precio, String tipoProducto) {
        this.IdProducto = id;
        this.claveProducto = claveProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.IdTipoProducto = tipoProducto;
    }

    public int getId() {
        return IdProducto;
    }

    public void setId(int id) {
        this.IdProducto = id;
    }

    public String getClaveProducto() {
        return claveProducto;
    }

    public void setClaveProducto(String claveProducto) {
        this.claveProducto = claveProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipoProducto() {
        return IdTipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.IdTipoProducto = tipoProducto;
    }
    
    
    
}
