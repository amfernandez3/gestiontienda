# Estructura del Proyecto Spring Boot

El proyecto sigue una estructura básica de una aplicación Spring Boot:

- **src/main/java:** Contiene los archivos fuente de Java.
  - **com.cursojava:** Paquete principal.
    - `TiendaApplication.java:` Clase principal para ejecutar la aplicación.

- **src/main/resources/static:** Contiene las páginas HTML.
- `pom.xml:` Archivo de configuración de Maven.

## Funcionalidades de la Aplicación

La aplicación consiste en un CRUD estándar de productos con las siguientes operaciones:

- **Búsquedas:** Permite buscar productos por sección.
- **Altas:** Permite dar de alta nuevos productos llenando todas sus propiedades.
- **Eliminación:** Permite la eliminación de un producto.
- **Modificación:** Permite cambiar el precio de un producto.

## Tecnologías Utilizadas

- Spring Boot
- Maven
- Servlets
- Java EE

**Servidor:**
- Tomcat
