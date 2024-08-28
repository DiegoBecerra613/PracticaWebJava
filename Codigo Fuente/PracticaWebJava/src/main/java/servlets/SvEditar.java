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
import logica.producto;
import logica.productoproveedor;

/**
 *
 * @author DiegoBP
 */
@WebServlet(name = "SvEditar", urlPatterns = {"/SvEditar"})
public class SvEditar extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_editar = Integer.parseInt(request.getParameter("IdProducto"));
        producto prod = control.traerProducto(id_editar);

        List<productoproveedor> listaProductosProveedores = control.traerProveedores(id_editar);
        HttpSession misesion = request.getSession();
        misesion.setAttribute("prodEditar", prod);
        misesion.setAttribute("listaProductosProveedores", listaProductosProveedores);
        misesion.setAttribute("id_producto", id_editar);
        response.sendRedirect("EditarProducto.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String claveProducto = request.getParameter("claveProducto");
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String tipoProducto = request.getParameter("tipoProducto");
        try {
            producto prod = (producto) request.getSession().getAttribute("prodEditar");
            prod.setClaveProducto(claveProducto);
            prod.setNombre(nombre);
            prod.setPrecio(precio);
            prod.setTipoProducto(tipoProducto);

            control.editarProducto(prod);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
