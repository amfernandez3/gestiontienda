package com.cursojava.gestiontienda.servlet;

import com.cursojava.gestiontienda.model.Producto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// ... (imports y otras declaraciones)

public class MostrarTablaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener o crear la sesión
        HttpSession session = request.getSession(true);

        // Obtener la lista de productos de la sesión
        List<Producto> listaProductos = (List<Producto>) session.getAttribute("listaProductos");

        // Si la lista no existe en la sesión, crear una nueva
        if (listaProductos == null) {
            listaProductos = Collections.emptyList();
        }

        // Configurar la respuesta como HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtener el parámetro de búsqueda
        String tipoBusqueda = request.getParameter("tipo");

        // Filtrar la lista de productos si se proporciona un tipo de búsqueda
        if (tipoBusqueda != null && !tipoBusqueda.isEmpty()) {
            listaProductos = filtrarPorTipo(listaProductos, tipoBusqueda);
        }

        // Generar el HTML de la tabla con los estilos de Bootstrap
        out.println("<html>");
        out.println("<head><title>Tabla de Productos</title>" +
                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">" +
                " <link rel=\"stylesheet\" href=\"/CSS/style.css\"></head>");
        out.println("<body>");
        out.println("<h2>Lista de productos:</h2>");
        out.println("<form class='mb-3' action='/MostrarTablaServlet' method='get'>");
        out.println("<div class='input-group'>");
        out.println("<input type='text' class='form-control' placeholder='Buscar por tipo' name='tipo'>");
        out.println("<button class='btn btn-outline-secondary' type='submit'>Buscar</button>");
        out.println("<a class='btn btn-secondary' href=\"/index.html\">Volver a la tienda</a>");
        out.println("</div>");
        out.println("</form>");
        out.println("<table class='table table-bordered'>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th scope='col'>NOMBRE</th>");
        out.println("<th scope='col'>SECCION</th>");
        out.println("<th scope='col'>PRECIO</th>");
        out.println("<th scope='col'>STOCK</th>");
        out.println("<th scope='col'>ACCIONES</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        for (Producto producto : listaProductos) {
            out.println("<tr>");
            out.println("<td>" + producto.getNombre() + "</td>");
            out.println("<td>" + producto.getSeccion() + "</td>");
            out.println("<td>" + producto.getPrecio() + "</td>");
            out.println("<td>" + producto.getStock() + "</td>");
            // Agregar botones para eliminar y modificar
            out.println("<td><a class='btn btn-danger btn-sm' href='/EliminarServlet?nombre=" + producto.getNombre() + "'>Eliminar</a>" +
                    "<a class='btn btn-primary btn-sm ms-1' href='/ModificarServlet?nombre=" + producto.getNombre() + "'>Modificar</a></td>");

            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

        out.flush();
    }

    private List<Producto> filtrarPorTipo(List<Producto> productos, String tipoBusqueda) {
        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getSeccion().toLowerCase().contains(tipoBusqueda.toLowerCase())) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }
}

