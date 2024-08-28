/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SvProductosProveedor", urlPatterns = {"/SvProductosProveedor"})
public class SvProductosProveedor extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession misesion = request.getSession();

            int id_producto = ((Integer) misesion.getAttribute("id_producto"));
            int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
            String claveProducto = request.getParameter("claveProducto");
            String costo = request.getParameter("costo");

            productoproveedor prodprovee = new productoproveedor();
            prodprovee.setIdProducto(id_producto);
            prodprovee.setIdProveedor(idProveedor);
            prodprovee.setClaveProductoProveedor(claveProducto);
            prodprovee.setCosto(costo);

            control.crearProductoProveedor(prodprovee);

            List<productoproveedor> listaProductosProveedores = control.traerProveedores(id_producto);
            misesion.setAttribute("listaProductosProveedores", listaProductosProveedores);
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
