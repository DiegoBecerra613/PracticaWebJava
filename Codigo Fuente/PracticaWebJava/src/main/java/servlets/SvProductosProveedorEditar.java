/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.productoproveedor;

/**
 *
 * @author DiegoBP
 */
@WebServlet(name = "SvProductosProveedorEditar", urlPatterns = {"/SvProductosProveedorEditar"})
public class SvProductosProveedorEditar extends HttpServlet {
    Controladora control = new Controladora();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProductoProveedor = Integer.parseInt(request.getParameter("editIdProdProvee"));
        int idProveedor = Integer.parseInt(request.getParameter("claveProducto"));
        String ClaveProductoProveedor = request.getParameter("nombre");
        String Costo = request.getParameter("precio");
        
        productoproveedor prodprovee = control.traerProveedor(idProductoProveedor);
        prodprovee.setIdProveedor(idProveedor);
        prodprovee.setClaveProductoProveedor(ClaveProductoProveedor);
        prodprovee.setCosto(Costo);
        
        control.editProdutoProveedor(prodprovee);
        HttpSession misesion = request.getSession();
        int id_producto = ((Integer) misesion.getAttribute("id_producto"));
        List<productoproveedor> listaProductosProveedores = control.traerProveedores(id_producto);
        misesion.setAttribute("listaProductosProveedores", listaProductosProveedores);
        response.sendRedirect("EditarProducto.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProductoProveedor = Integer.parseInt(request.getParameter("editIdProdProvee"));
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        String ClaveProductoProveedor = request.getParameter("claveProducto");
        String Costo = request.getParameter("costo");
        
        productoproveedor prodprovee = control.traerProveedor(idProductoProveedor);
        prodprovee.setIdProveedor(idProveedor);
        prodprovee.setClaveProductoProveedor(ClaveProductoProveedor);
        prodprovee.setCosto(Costo);
        
        control.editProdutoProveedor(prodprovee);
        HttpSession misesion = request.getSession();
        int id_producto = ((Integer) misesion.getAttribute("id_producto"));
        List<productoproveedor> listaProductosProveedores = control.traerProveedores(id_producto);
        misesion.setAttribute("listaProductosProveedores", listaProductosProveedores);
        response.sendRedirect("EditarProducto.jsp");
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
