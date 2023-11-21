package com.cursojava.gestiontienda.servlet;


import com.cursojava.gestiontienda.model.Producto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class EliminarServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombreProducto = request.getParameter("nombre");

        // Lógica para eliminar el producto de la lista en la sesión
        HttpSession session = request.getSession();
        List<Producto> listaProductos = (List<Producto>) session.getAttribute("listaProductos");

        // Eliminar el producto con el nombre especificado
        listaProductos.removeIf(producto -> producto.getNombre().equals(nombreProducto));

        // Actualizar la lista en la sesión
        session.setAttribute("listaProductos", listaProductos);

        // Redirigir nuevamente al servlet que muestra la tabla
        response.sendRedirect(request.getContextPath() + "/MostrarTablaServlet");
    }
}
