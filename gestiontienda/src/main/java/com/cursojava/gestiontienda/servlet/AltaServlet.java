package com.cursojava.gestiontienda.servlet;


import com.cursojava.gestiontienda.model.Producto;
import jakarta.servlet.*;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.*;

public class AltaServlet extends HttpServlet implements Servlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //********************************************** Gestión de parámetros e instancias

        // Obtener parámetros del formulario.
        String nombre = request.getParameter("nombre");
        String seccion = request.getParameter("seccion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        // Crear un nuevo producto usando el constructor.
        Producto nuevoProducto = new Producto(nombre,seccion,precio,stock);

        //************************************************** Lógica de la sesión
        // Obtener o crear la sesión
        HttpSession session = request.getSession(true);

        // Obtener la lista de productos de la sesión parseando la info de la session
        List<Producto> listaProductos = (List<Producto>) session.getAttribute("listaProductos");

        // Si la lista no existe en la sesión, crear una nueva
        if (listaProductos == null) {
            listaProductos = new ArrayList<>();
        }

        // Agregar el nuevo producto a la lista
        listaProductos.add(nuevoProducto);

        // Actualizar la lista en la sesión
        session.setAttribute("listaProductos", listaProductos);

        //****************************************************************Testeo de datos

        List<Producto> listaProductosActualizada = (List<Producto>) session.getAttribute("listaProductos");

        System.out.println("Productos en la sesión después de la actualización:");
        for (Producto producto : listaProductosActualizada) {
            System.out.println(producto.toString());
        }
        //*****************************************************************Gestión de salida y redirección
        // Redirigir a la página principal o mostrar un mensaje de éxito
        // Después de agregar el producto a tu base de datos o almacenamiento
        response.sendRedirect(request.getContextPath() + "/index.html?successMessage=El+producto+fue+incluido+con+exito!");

    }

}