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
public class proveedor implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int IdProveedor;
    private String Nombre;
    private String Descripcion;

    public proveedor() {
    }

    public proveedor(int IdProveedor, String Nombre, String Descripcion) {
        this.IdProveedor = IdProveedor;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
}
