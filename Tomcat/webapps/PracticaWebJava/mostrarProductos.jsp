<%-- 
    Document   : mostrarProductos
    Created on : 25 ago 2024, 22:39:41
    Author     : DiegoBP
--%>

<%@page import="java.util.List"%>
<%@page import="logica.producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Productos</title>
    </head>
    <body>
        <h1>Lista de Productos</h1>
        <%
            List<producto> listaProductos = (List) request.getSession().getAttribute("listaProductos");
            int cont = 1;
            for(producto usu : listaProductos){
        %>
        
        <p><b>Producto N°<%=cont%></b></p>
        <p>Clave Producto: <%=usu.getClaveProducto()%></p>
        <p>Nombre: <%=usu.getNombre()%></p>
        <p>Precio: <%=usu.getPrecio()%></p>
        <p>Tipo Producto: <%=usu.getTipoProducto()%></p>
        
        <% cont = cont + 1;
            } %>
    </body>
</html>
