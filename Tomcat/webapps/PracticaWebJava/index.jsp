<%@page import="logica.tipoproducto"%>
<%@page import="java.util.List"%>
<%@page import="logica.producto"%>
<%@page import="logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Productos</title>
        <%
            // Crear instancia de Controladora
            Controladora control = new Controladora();
            List<tipoproducto> listaTipos = control.traerTipos();
        %>
        <link rel="stylesheet" type="text/css" href="src/css/style.css">
    </head>
    <body>
        <h1>Lista de Productos</h1>
        <!-- Formulario de búsqueda -->
        <div class="search-container">
            <form action="index.jsp" method="GET" style="display: flex; align-items: center;">
                <p>
                    <label for="claveProducto">Clave Producto: </label>
                    <input type="text" id="claveProducto" name="claveProducto" value="<%= request.getParameter("claveProducto") != null ? request.getParameter("claveProducto") : ""%>">
                </p>
                <p>
                    <label for="tipoProducto">Tipo Producto: </label>
                    <select id="tipoProducto" name="tipoProducto">
                        <option value=""></option>
                        <% for (tipoproducto tipo : listaTipos) {%>
                        <option value="<%= tipo.getId()%>"><%= tipo.getNombre()%></option>
                        <% } %>
                    </select>
                </p>
                <button type="submit">Buscar</button>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Editar</th>
                    <th>Clave Producto</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Obtener parámetros de búsqueda
                    String claveProducto = request.getParameter("claveProducto");
                    String tipoProducto = request.getParameter("tipoProducto");

                    // Obtener lista de productos basados en la búsqueda
                    List<producto> listaProductos;

                    if (claveProducto != null || tipoProducto != null) {
                        listaProductos = control.buscarProductos(claveProducto, tipoProducto);
                    } else {
                        listaProductos = control.traerProductos();
                    }

                    if (listaProductos != null && !listaProductos.isEmpty()) {
                        for (producto prod : listaProductos) {
                %>
                <tr>
                    <td class="action-buttons">
                        <form action="SvEditar" method="GET">
                            <input type="hidden" name="IdProducto" value="<%= prod.getId()%>">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td><%= prod.getClaveProducto()%></td>
                    <td><%= prod.getNombre()%></td>
                    <td><%= prod.getPrecio()%></td>
                    <td class="action-buttons">
                        <form action="SvEliminar" method="POST">
                            <input type="hidden" name="IdProducto" value="<%= prod.getId()%>">
                            <button type="submit" class="delete">Eliminar</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="5">No se encontraron productos.</td>
                </tr>
                <% }%>
            </tbody>
        </table>
        <h3><a href="EditarProducto.jsp?accion=agregar">Agregar Nuevo Producto</a></h3>
    </body>
</html>