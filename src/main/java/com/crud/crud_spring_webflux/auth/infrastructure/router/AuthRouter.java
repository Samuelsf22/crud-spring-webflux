package com.crud.crud_spring_webflux.auth.infrastructure.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.crud.crud_spring_webflux.auth.infrastructure.handler.AuthHandler;



@Configuration
public class AuthRouter {

    private static final String PATH = "auth/";

    @Bean
    RouterFunction<ServerResponse> authRoute(AuthHandler authHandler) {
        return RouterFunctions.route()
                .POST(PATH + "login", authHandler::login)
                .POST(PATH + "create", authHandler::create)
                .build();
    }

}
