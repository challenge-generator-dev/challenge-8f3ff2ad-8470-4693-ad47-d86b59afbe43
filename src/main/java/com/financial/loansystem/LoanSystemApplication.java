package com.financial.loansystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
@EnableWebFlux
@OpenAPIDefinition(info = @Info(title = "Loan System API", version = "1.0", description = "API para procesamiento de solicitudes de préstamos con validación e2e"))
public class LoanSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanSystemApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> healthCheckRoute() {
        return route(GET("/health"),
            request -> ServerResponse.ok().bodyValue("Loan System is running"));
    }
}