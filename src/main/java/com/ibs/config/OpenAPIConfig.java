package com.ibs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Processing Stub API")
                        .version("2.0.0")
                        .description("REST API for emulating processing system in microfinance")
                        .contact(new Contact()
                                .name("Roman Mironov")
                                .email("exampleMail@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .servers(getServers());
    }

    private List<Server> getServers() {
        return List.of(
                new Server()
                        .url("http://localhost:" + serverPort)
                        .description("Current server (" + getProfileDescription() + ")"),
                new Server()
                        .url("http://localhost:8080")
                        .description("Default profile (port 8080, delay 1s)"),
                new Server()
                        .url("http://localhost:7770")
                        .description("Test1 profile (port 7770, delay 2s)"),
                new Server()
                        .url("http://localhost:7775")
                        .description("Test2 profile (port 7775, delay 4s)")
        );
    }

    private String getProfileDescription() {
        return switch (activeProfile) {
            case "test1" -> "Test1 profile - port 7770, delay 2s";
            case "test2" -> "Test2 profile - port 7775, delay 4s";
            default -> "Default profile - port 8080, delay 1s";
        };
    }
}