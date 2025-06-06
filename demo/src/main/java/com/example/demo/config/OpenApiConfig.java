package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${dominio.openapi.dev-url}")
    private String devUrl;
    @Value("${dominio.openapi.prod-url}")
    private String prodUrl;

    @Bean
    OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("URL do servidor de desenvolvimento");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("URL do servidor de produção");

        Contact contact = new Contact();
        contact.setEmail("contato@meudominio.com.br");
        contact.setName("Serratec");
        contact.setUrl("https://www.meudominio.com.br");

        License apacheLicense = new License().name("Apache License")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info().title("API Residência Projeto Final")
                .version("1.0")
                .contact(contact)
                .description("API desenvolvida para o projeto final da residência 2025-01, desenvolvida por: Taiane Albuquerque, Patricia Sanches, Raílla Duarte, Matheus Ruella, Maxwel Robson")
                .termsOfService("https://www.meudominio.com.br/termos")
                .license(apacheLicense);

        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Insira o token JWT no campo abaixo. Exemplo: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer))
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes("Authorization", securityScheme));
    }
}
