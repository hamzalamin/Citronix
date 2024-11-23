package com.wora.citronix.configs;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI defineApi(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development server");

        Contact contact = new Contact();
        contact.setName("Hamza LAMIN");
        contact.setEmail("hamzalamin80@gmail.com");
        contact.setUrl("https://www.linkedin.com/in/hamza-lamin-a0440a296/");

        Info info = new  Info().title("API REST Citronix")
                .version("0.1")
                .description("Citronix is a comprehensive application designed to streamline the management of a lemon farm. The application enables farmers to efficiently track production, harvest, and sales of their products.\n" +
                        "\n" +
                        "It provides tools to manage farms, fields, trees, harvests, and sales while optimizing the monitoring of tree productivity based on their age.\n" +
                        "\n" +
                        "With Citronix, farmers can enhance operational efficiency and gain insights into their farm's performance to make informed decisions.")
                .contact(contact);
        return new OpenAPI().info(info).servers(List.of(server));
    }
}
