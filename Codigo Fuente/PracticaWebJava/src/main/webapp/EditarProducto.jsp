<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="logica.proveedor"%>
<%@page import="logica.productoproveedor"%>
<%@page import="logica.tipoproducto"%>
<%@page import="java.util.List"%>
<%@page import="logica.Controladora"%>
<%@page import="logica.producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= (request.getParameter("accion") != null && request.getParameter("accion").equals("agregar")) ? "Agregar Producto" : "Editar Producto"%></title>
        <link rel="stylesheet" type="text/css" href="src/css/style.css">
        <style>
            .form-container {
                display: grid;
                grid-template-columns: repeat(2, 1fr);
                gap: 10px;
                margin: auto;
                margin-bottom: 20px;
            }
            .form-container p {
                margin: 0;
                display: flex;
                align-items: center;
            }
            .form-container label {
                width: 150px; /* Ajusta este ancho según sea necesario */
                display: inline-block;
            }
            .form-container input, .form-container select {
                width: 100%;
                margin-right: 20px;
                padding: 5px;
                font-size: 16px;
            }
            .form-container button {
                grid-column: span 2; /* El botón ocupa ambas columnas */
            }
            .action-buttons {
                text-align: center;
            }
            .action-buttons button {
                margin: 0 5px;
            }
            .button-container {
                text-align: center;
                margin-top: 20px;
            }
            .button-container button {
                padding: 10px 20px;
                font-size: 16px;
                margin: 0 10px;
            }
        </style>
        <script>
            // Función para abrir el modal de agregar proveedor
            function openAddModal() {
                document.getElementById("addModal").style.display = "block";
            }

            // Función para abrir el modal de editar proveedor
            function openEditModal(proveedor) {
                document.getElementById("editModal").style.display = "block";
                document.getElementById("editIdProdProvee").value = proveedor.id;
                document.getElementById("claveProducto").value = proveedor.claveProducto;
                document.getElementById("costo").value = proveedor.costo;

                const select = document.getElementById("idProveedor2");
                const proveedorId = proveedor.idProveedor.toString();
                select.value = proveedorId;

                select.dispatchEvent(new Event('change')); // Forzar el evento de cambio
            }

            // Función para cerrar el modal
            function closeModal(modalId) {
                document.getElementById(modalId).style.display = "none";
            }
        </script>
    </head>
    <body>
        <h1><%= (request.getParameter("accion") != null && request.getParameter("accion").equals("agregar")) ? "Agregar Producto" : "Editar Producto"%></h1>
        <%
            Controladora control = new Controladora();
            List<tipoproducto> listaTipos = control.traerTipos();
            List<proveedor> listaProveedores = control.traerProveedores();

            // Crear un mapa de IDs de proveedores a nombres de proveedores
            Map<Integer, String> proveedorMap = new HashMap<>();
            for (proveedor p : listaProveedores) {
                proveedorMap.put(p.getIdProveedor(), p.getNombre());
            }

            producto prod = (producto) request.getSession().getAttribute("prodEditar");
            String accion = request.getParameter("accion");
            String formAction = "SvEditar";
            String buttonText = "Guardar";

            if (accion != null && accion.equals("agregar")) {
                prod = new producto(); // Crear un nuevo objeto producto vacío para agregar
                formAction = "SvProductos"; // Cambia el action del formulario a SvAgregar
                buttonText = "Agregar";
            }
        %>

        <form action="<%= formAction%>" method="POST">
            <div class="form-container">
                <p><label>Clave Producto: </label> <input type="text" name="claveProducto" value="<%= prod.getClaveProducto() != null ? prod.getClaveProducto() : ""%>" required></p>
                <p><label>Nombre: </label> <input type="text" name="nombre" value="<%= prod.getNombre() != null ? prod.getNombre() : ""%>"required></p>
                <p><label>Precio: </label> <input type="text" name="precio" value="<%= prod.getPrecio() != null ? prod.getPrecio() : ""%>" required></p>
                <p><label>Tipo Producto: </label>
                    <select id="tipoProducto" name="tipoProducto">
                        <%
                            String selectedTipoId = String.valueOf(prod.getTipoProducto());
                            for (tipoproducto tipo : listaTipos) {
                                String tipoIdStr = String.valueOf(tipo.getId());
                                boolean isSelected = tipoIdStr.equals(selectedTipoId);
                        %>
                        <option value="<%= tipoIdStr%>" <%= isSelected ? "selected" : ""%>><%= tipo.getNombre()%></option>
                        <%
                            }
                        %>
                    </select>
                </p>
                <button type="submit"><%= buttonText%></button>
            </div>
        </form>

        <% if (accion == null || !accion.equals("agregar")) { %>
        <table>
            <thead>
                <tr>
                    <th>Editar</th>
                    <th>Proveedor</th>
                    <th>Clave Producto</th>
                    <th>Costo</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<productoproveedor> listaProductosProveedores = (List) request.getSession().getAttribute("listaProductosProveedores");
                    int cont = 1;
                    if (listaProductosProveedores != null && !listaProductosProveedores.isEmpty()) {
                        for (productoproveedor prodprovee : listaProductosProveedores) {
                            String proveedorNombre = proveedorMap.get(prodprovee.getIdProveedor()); // Obtener el nombre del proveedor
%>
                <tr>
                    <td class="action-buttons">
                        <button onclick="openEditModal({
                                    id: '<%= prodprovee.getIdProductoProveedor()%>',
                                    idProveedor: '<%= prodprovee.getIdProveedor()%>',
                                    costo: '<%= prodprovee.getCosto()%>',
                                    claveProducto: '<%= prodprovee.getClaveProductoProveedor()%>'
                                            // Puedes añadir más datos aquí si es necesario
                                })">Editar</button>
                    </td>
                    <td><%= proveedorNombre != null ? proveedorNombre : "Desconocido"%></td>
                    <td><%= prodprovee.getClaveProductoProveedor()%></td>
                    <td><%= prodprovee.getCosto()%></td>
                    <td class="action-buttons">
                        <form action="SvEliminarProductoProveedor" method="POST" style="display:inline;">
                            <input type="hidden" name="IdProductoProovedor" value="<%= prodprovee.getIdProductoProveedor()%>">
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
                <% } %>
            </tbody>
        </table>
        <div class="button-container">
            <!-- Botón para abrir el modal de agregar proveedor -->
            <button onclick="openAddModal()" class="button-action">Agregar proveedor</button>
        </div>
        <% } %>
        <div class="button-container">
            <!-- Botón para regresar a index.jsp -->
            <form action="index.jsp" method="GET" style="display:inline;">
                <button type="submit">Ir hacia atrás</button>
            </form>
        </div>

        <!-- Modal para agregar proveedor -->
        <div id="addModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal('addModal')">&times;</span>
                <h2>Agregar Proveedor</h2>
                <form action="SvProductosProveedor" method="POST" class="form-container">
                    <p><label>Proveedor: </label> 
                        <select id="idProveedor" name="idProveedor">
                            <%
                                for (proveedor provee : listaProveedores) {
                            %>
                            <option value="<%= provee.getIdProveedor()%>"><%= provee.getNombre()%></option>
                            <%
                                }
                            %>
                        </select>
                    </p>
                    <p><label>Clave Producto: </label> <input type="text" name="claveProducto" required></p>
                    <p><label>Costo: </label> <input type="text" name="costo" required></p>
                    <button type="submit">Agregar</button>
                </form>
            </div>
        </div>

        <!-- Modal para editar proveedor -->
        <div id="editModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal('editModal')">&times;</span>
                <h2>Editar Proveedor</h2>
                <form action="SvProductosProveedorEditar" method="POST" class="form-container">
                    <input type="hidden" id="editIdProdProvee" name="editIdProdProvee">
                    <p><label>Proveedor: </label> 
                        <select id="idProveedor2" name="idProveedor">
                            <%
                                for (proveedor provee : listaProveedores) {
                            %>
                            <option value="<%= provee.getIdProveedor()%>"><%= provee.getNombre()%></option>
                            <%
                                }
                            %>
                        </select>
                    </p>
                    <p><label>Clave Producto: </label> <input type="text" name="claveProducto" id="claveProducto" required></p>
                    <p><label>Costo: </label> <input type="text" name="costo" id="costo" required></p>
                    <button type="submit">Editar</button>
                </form>
            </div>
        </div>
    </body>
</html>