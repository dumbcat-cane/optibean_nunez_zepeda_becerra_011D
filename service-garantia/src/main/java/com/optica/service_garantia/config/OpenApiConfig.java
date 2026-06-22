package com.optica.service_garantia.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Garantias | Servicio de Garantias")
                .version("1.0")
                .description("Documentación centralizada del Sistema de garantias"))
        
            .servers(List.of(
                new Server().url("http://localhost:8081").description("Servidor a través del Gateway")
            ));
    }

}
