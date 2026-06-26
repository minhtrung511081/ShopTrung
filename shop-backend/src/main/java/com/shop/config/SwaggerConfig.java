package com.shop.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    String scheme = "bearerAuth";


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
                // 🔥 QUAN TRỌNG: khai báo security operation-level override
                .schemaRequirement(scheme, new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"))

                .addSecurityItem(new SecurityRequirement().addList(scheme))
                .externalDocs(new ExternalDocumentation()
                        .description("Shop Documentation"));

    }


}