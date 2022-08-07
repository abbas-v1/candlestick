package com.baraka.candlestick.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Open API configuration
 *
 * @see Swagger UI : /swagger-ui/index.html
 * @see Open API JSON doc : /v3/api-docs
 * @see Open API YAML doc : /v3/api-docs.yaml
 *
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }

}
