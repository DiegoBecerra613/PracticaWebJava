<%-- 
    Document   : error
    Created on : 28 ago 2024, 11:58:06
    Author     : DiegoBP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error</title>
        <link rel="stylesheet" type="text/css" href="src/css/style.css">
    </head>
    <body>
        <h1>¡Ups! Algo salió mal</h1>
        <p>Lo sentimos, pero ocurrió un error en el procesamiento de tu solicitud.</p>
        <p>Por favor, intenta nuevamente más tarde o contacta al administrador del sitio.</p>
        <div class="button-container">
            <!-- Botón para regresar a index.jsp -->
            <form action="index.jsp" method="GET" style="display:inline;">
                <button type="submit">Ir hacia atrás</button>
            </form>
        </div>
    </body>
</html>
