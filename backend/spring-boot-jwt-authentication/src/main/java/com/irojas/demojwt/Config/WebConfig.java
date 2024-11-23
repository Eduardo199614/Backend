package com.irojas.demojwt.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configurar el acceso a imágenes almacenadas en C:/ImagenStreaming
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:C:/ImagenStreaming/")
                .setCachePeriod(3600); // Opcional: Configura caché en segundos
    }
}