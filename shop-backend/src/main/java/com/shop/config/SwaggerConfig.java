package com.shop.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI shopAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Shop API")
                        .description("REST API Website bán hàng")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Vo Minh Trung")
                                .email("trung@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Shop Documentation"));

    }

}