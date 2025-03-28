package com.aalvarezg.ms_publications.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Ms Posteos o publicaciones")
                        .version("1.0")
                        .description("Documentación de la API de Posteos")
                        .contact(new Contact()
                                .name("Alexander Alvarez")
                                .email("ing.alexanderg1998@gmail.com")
                                .url("https://www.linkedin.com/in/alex-alvarez-594397166/")));
    }
}
