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

        // Generar el HTML de la tabla
        out.println("<html>");
        out.println("<head><title>Tabla de Productos</title></head>");
        out.println("<body>");
        out.println("<h2>Lista de productos:</h2>");
        out.println("<form action='/MostrarTablaServlet' method='get'>");
        out.println("Buscar por tipo: <input type='text' name='tipo'>");
        out.println("<input type='submit' value='Buscar'>");
        out.println("</form>");
        out.println("<table border='1'>");
        out.println("<tr><th>Nombre</th><th>Sección</th><th>Precio</th><th>Stock</th></tr>");

        for (Producto producto : listaProductos) {
            out.println("<tr>");
            out.println("<td>" + producto.getNombre() + "</td>");
            out.println("<td>" + producto.getSeccion() + "</td>");
            out.println("<td>" + producto.getPrecio() + "</td>");
            out.println("<td>" + producto.getStock() + "</td>");
            // Agregar botones para eliminar y modificar
            out.println("<td><a href='/EliminarServlet?nombre=" + producto.getNombre() + "'>Eliminar</a></td>");
            out.println("<td><a href='/ModificarServlet?nombre=" + producto.getNombre() + "'>Modificar</a></td>");

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<a href=\"/index.html\">Volver a la tienda</a>");
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

