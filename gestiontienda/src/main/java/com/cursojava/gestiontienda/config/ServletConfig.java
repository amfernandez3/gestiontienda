package com.cursojava.gestiontienda.config;

import com.cursojava.gestiontienda.servlet.*;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    /**
     * Servlet que responde a la petición HTTP /AltaServlet
     * @return respuesta a la petición HTTP
     */
    @Bean
    public ServletRegistrationBean<AltaServlet> AltaservletRegistrationBean() {
        return new ServletRegistrationBean<>(new AltaServlet(), "/AltaServlet");
    }

    /**
     * Servlet que responde a la petición HTTP /MostrarTablaServlet
     * @return respuesta a la petición HTTP
     */
    @Bean
    public ServletRegistrationBean<MostrarTablaServlet> MostrarTablaServletRegistrationBean() {
        return new ServletRegistrationBean<>(new MostrarTablaServlet(), "/MostrarTablaServlet");
    }

    /**
     * Servlet que responde a la petición HTTP /EliminarServlet
     * @return respuesta a la petición HTTP
     */
    @Bean
    public ServletRegistrationBean<EliminarServlet> EliminarServletRegistrationBean() {
        return new ServletRegistrationBean<>(new EliminarServlet(), "/EliminarServlet");
    }

    /**
     * Servlet que responde a la petición HTTP /ModificarServlet
     * @return respuesta a la petición HTTP
     */
    @Bean
    public ServletRegistrationBean<ModificarServlet> ModificarServletRegistrationBean() {
        return new ServletRegistrationBean<>(new ModificarServlet(), "/ModificarServlet");
    }

    /**
     * Servlet que responde a la petición HTTP /ModificarServlet
     * @return respuesta a la petición HTTP
     */
    @Bean
    public ServletRegistrationBean<AgregarProductosEjemploServlet> AgregarproductosEjemploServletRegistrationBean() {
        return new ServletRegistrationBean<>(new AgregarProductosEjemploServlet(), "/AgregarProductosEjemploServlet");
    }

}