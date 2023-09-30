package com.life.courses.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Courses Master Api",
                description = "Courses Master", version = "1.0.0",
                contact = @Contact(
                        name = "Egor Martynov",
                        email = "ekip-s@yandex.ru",
                        url = "https://"
                )
        )
)
public class OpenApiConfig {
}
