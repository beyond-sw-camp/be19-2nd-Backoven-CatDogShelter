package com.backoven.catdogshelter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/* 설명. http://localhost:8080/swagger-ui/index.html */
@OpenAPIDefinition(
        info = @Info(
                title = "댕냥쉼터 REST API 명세서",
                description = "댕냥 쉼터 REST API를 설명하기 위한 명세서",
                version = "v1.0.0"
        )
)
@Configuration
public class SwaggerConfiguration {
}
