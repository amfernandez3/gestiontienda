package com.cursojava.gestiontienda.servlet;


import com.cursojava.gestiontienda.model.Producto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ModificarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nombreProducto = request.getParameter("nombre");

        // Lógica para obtener el producto con el nombre especificado de la sesión
        HttpSession session = request.getSession();
        List<Producto> listaProductos = (List<Producto>) session.getAttribute("listaProductos");

        Producto productoAModificar = listaProductos.stream()
                .filter(producto -> producto.getNombre().equals(nombreProducto))
                .findFirst()
                .orElse(null);

        // Verificar si se encontró el producto
        if (productoAModificar != null) {
            // Puedes almacenar el producto a modificar en la sesión para usarlo en la solicitud POST
            session.setAttribute("productoAModificar", productoAModificar);

            // Redirigir al formulario de modificación
            RequestDispatcher dispatcher = request.getRequestDispatcher("/modificar.html");
            dispatcher.forward(request, response);
        } else {
            // Manejar el caso en que el producto no se encuentre
            response.getWriter().println("Producto no encontrado");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener los parámetros del formulario de modificación
        String nuevoNombre = request.getParameter("nuevoNombre");
        String nuevaSeccion = request.getParameter("nuevaSeccion");
        double nuevoPrecio = Double.parseDouble(request.getParameter("nuevoPrecio"));
        int nuevoStock = Integer.parseInt(request.getParameter("nuevoStock"));

        // Crear un nuevo producto con los datos modificados
        Producto productoModificado = new Producto(nuevoNombre, nuevaSeccion, nuevoPrecio, nuevoStock);

        // Actualizar el producto en la lista de la sesión
        HttpSession session = request.getSession();
        List<Producto> listaProductos = (List<Producto>) session.getAttribute("listaProductos");

        Producto productoAModificar = (Producto) session.getAttribute("productoAModificar");

        if (productoAModificar != null) {
            // Reemplazar el producto antiguo con el modificado en la lista
            listaProductos.remove(productoAModificar);
            listaProductos.add(productoModificado);

            // Actualizar la lista en la sesión
            session.setAttribute("listaProductos", listaProductos);

            // Limpiar la información de producto a modificar de la sesión
            session.removeAttribute("productoAModificar");

            // Redirigir nuevamente al servlet que muestra la tabla
            response.sendRedirect(request.getContextPath() + "/MostrarTablaServlet");
        } else {
            // Manejar el caso en que no se encuentre el producto a modificar
            response.getWriter().println("Producto a modificar no encontrado");
        }
    }
}
