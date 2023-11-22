package com.cursojava.gestiontienda.servlet;

import com.cursojava.gestiontienda.model.Producto;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AgregarProductosEjemploServlet")
public class AgregarProductosEjemploServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Crear una lista de productos de ejemplo
        List<Producto> productosEjemplo = new ArrayList<>();
        productosEjemplo.add(new Producto("Laptop", "Electrónicos", 1200.0, 50));
        productosEjemplo.add(new Producto("Camisa Polo", "Ropa", 35.0, 100));
        productosEjemplo.add(new Producto("Sofá Reclinable", "Hogar", 500.0, 20));
        productosEjemplo.add(new Producto("Auriculares Inalámbricos", "Electrónicos", 80.0, 75));
        productosEjemplo.add(new Producto("Vestido de Noche", "Ropa", 120.0, 30));
        productosEjemplo.add(new Producto("Juego de Sábanas", "Hogar", 45.0, 60));
        productosEjemplo.add(new Producto("Smartphone", "Electrónicos", 600.0, 90));
        productosEjemplo.add(new Producto("Zapatillas Deportivas", "Ropa", 50.0, 40));
        productosEjemplo.add(new Producto("Mesa de Centro", "Hogar", 150.0, 25));
        productosEjemplo.add(new Producto("Cámara Digital", "Electrónicos", 300.0, 15));
        productosEjemplo.add(new Producto("Abrigo de Invierno", "Ropa", 80.0, 55));
        productosEjemplo.add(new Producto("Aspiradora Robótica", "Hogar", 200.0, 10));
        productosEjemplo.add(new Producto("Altavoces Bluetooth", "Electrónicos", 70.0, 80));
        productosEjemplo.add(new Producto("Jeans Desgastados", "Ropa", 45.0, 70));
        productosEjemplo.add(new Producto("Lámpara de Pie", "Hogar", 80.0, 30));
        productosEjemplo.add(new Producto("Tablet", "Electrónicos", 250.0, 45));
        productosEjemplo.add(new Producto("Chaqueta de Cuero", "Ropa", 100.0, 50));
        productosEjemplo.add(new Producto("Juego de Toallas", "Hogar", 30.0, 100));
        productosEjemplo.add(new Producto("Impresora Multifunción", "Electrónicos", 120.0, 25));
        productosEjemplo.add(new Producto("Sombrero de Paja", "Ropa", 20.0, 80));


        // Obtener la sesión
        HttpSession session = request.getSession(true);

        // Obtener la lista de productos de la sesión
        List<Producto> listaProductos = (List<Producto>) session.getAttribute("listaProductos");

        // Si la lista no existe en la sesión, crea una nueva
        if (listaProductos == null) {
            listaProductos = new ArrayList<>();
        }

        // Agregar productos de ejemplo a la lista de productos en la sesión
        listaProductos.addAll(productosEjemplo);

        // Actualizar la lista en la sesión
        session.setAttribute("listaProductos", listaProductos);

        // Redirigir a la página de inicio o a donde desees después de agregar los productos
        response.sendRedirect("/index.html");
    }
}
