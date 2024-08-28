/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.producto;
import logica.productoproveedor;

/**
 *
 * @author DiegoBP
 */
@WebServlet(name = "SvProductos", urlPatterns = {"/SvProductos"})
public class SvProductos extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<producto> listaProductos = new ArrayList<>();
        listaProductos = control.traerProductos();
        HttpSession misesion = request.getSession();
        misesion.setAttribute("listaProductos", listaProductos);

        response.sendRedirect("mostrarProductos.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String claveProducto = request.getParameter("claveProducto");
            String nombre = request.getParameter("nombre");
            String precio = request.getParameter("precio");
            String tipoProducto = request.getParameter("tipoProducto");

            producto prod = new producto();
            prod.setClaveProducto(claveProducto);
            prod.setNombre(nombre);
            prod.setPrecio(precio);
            prod.setTipoProducto(tipoProducto);

            control.crearProducto(prod);
            int id_editar = prod.getId();

            List<productoproveedor> listaProductosProveedores = control.traerProveedores(id_editar);
            HttpSession misesion = request.getSession();
            misesion.setAttribute("prodEditar", prod);
            misesion.setAttribute("listaProductosProveedores", listaProductosProveedores);
            misesion.setAttribute("id_producto", id_editar);
            response.sendRedirect("EditarProducto.jsp");
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
