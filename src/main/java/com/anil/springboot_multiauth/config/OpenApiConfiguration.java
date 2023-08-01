package com.anil.springboot_multiauth.config;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Contact;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.info.License;
//import io.swagger.v3.oas.annotations.servers.Server;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@OpenAPIDefinition(
//        info = @Info(
//                contact = @Contact(
//                        name = "Anil Kumar",
//                        email = "anilkumarhv04@gmail.com",
//                        url = ""
//                ),
//                description = "OpenApi documentation for Multi Authentication application",
//                title = "Spring Boot Multi Authentication",
//                version = "1.0.0",
//                license = @License(
//                        name = "Licence name",
//                        url = "https://someurl.com"
//                ),
//                termsOfService = "Terms of Service"
//        ),
//        servers = {
//                @Server(
//                        description = "LOCAL Env",
//                        url = "http://localhost:8080"
//                ),
//                @Server(
//                        description = "DEV Env",
//                        url = "http://localhost:8080"
//                )
//        }
//)
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI multiAuthOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Multi Auth API")
                        .description("OpenApi documentation for Multi Authentication application")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                        )
                        .contact(new Contact()
                                .name("Anil Kumar")
                                .email("anilkumarhv04@gmail.com")
                                .url("")
                        )
                        .termsOfService("Terms oc Service")
                )
                .servers(Arrays.asList(
                        new Server().description("LOCAL ENV").url("http://localhost:8080"),
                        new Server().description("DEV ENV").url("http://localhost:8080")
                ))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs")
                )
                .components(
                        new Components()
                                .addSecuritySchemes("bearerAuth",
                                        new SecurityScheme()
                                                .name("bearerAuth")
                                                .description("JWT auth description")
                                                .scheme("bearer")
                                                .type(SecurityScheme.Type.HTTP)
                                                .bearerFormat("JWT")
                                                .in(SecurityScheme.In.HEADER)
                                )
                )
                ;
    }


    @Bean
    public SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name("bearerAuth")
                .description("JWT auth description")
                .scheme("bearer")
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);
    }

}
