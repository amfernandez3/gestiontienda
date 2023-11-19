// script.js

window.onload = function() {
    var urlParams = new URLSearchParams(window.location.search);
    var successMessage = urlParams.get('successMessage');

    if (successMessage) {
        alert(successMessage);

        // Elimina el parámetro de la URL (historial del navegador)
        var newurl = window.location.href.split("?")[0];
        window.history.pushState({ path: newurl }, '', newurl);
    }

    // Lógica para obtener y mostrar la lista de productos
    var listaProductos = obtenerProductosDesdeSesion();
    console.log("Contenido de sessionStorage:", sessionStorage);
    mostrarProductosEnTabla(listaProductos);
};

function obtenerProductosDesdeSesion() {
    // Intenta obtener la lista de productos desde sessionStorage
    var listaProductos = [];

    // Ajusta 'listaProductos' según el nombre real que usas en el servlet
    var listaProductosEnSession = sessionStorage.getItem('listaProductos');
    console.log(listaProductosEnSession);
    // Verifica si la lista de productos en sessionStorage no es ni nula ni indefinida
    if (listaProductosEnSession) {
        // Convierte la cadena JSON almacenada en sessionStorage a un objeto JavaScript
        listaProductos = JSON.parse(listaProductosEnSession);
    }

    return listaProductos;
}

function mostrarProductosEnTabla(productos) {
    // Seleccionar el div donde queremos mostrar la tabla
    var divTabla = document.getElementById('tabla-productos');

    // Crear la tabla
    var table = document.createElement('table');
    table.border = '1';

    var headerRow = table.insertRow(0);
    var headers = ['Nombre', 'Sección', 'Precio', 'Stock'];

    for (var i = 0; i < headers.length; i++) {
        var headerCell = headerRow.insertCell(i);
        headerCell.innerHTML = headers[i];
    }

    for (var i = 0; i < productos.length; i++) {
        var product = productos[i];
        var row = table.insertRow(i + 1);

        var cells = [product.nombre, product.seccion, product.precio, product.stock];

        for (var j = 0; j < cells.length; j++) {
            var cell = row.insertCell(j);
            cell.innerHTML = cells[j];
        }
    }

    // Limpiar el contenido existente en el div antes de agregar la nueva tabla
    divTabla.innerHTML = '';

    // Agregar la tabla al div
    divTabla.appendChild(table);
}
