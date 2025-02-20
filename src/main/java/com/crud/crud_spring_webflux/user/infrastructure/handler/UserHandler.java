package com.crud.crud_spring_webflux.user.infrastructure.handler;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.crud.crud_spring_webflux.user.domain.UserService;
import com.crud.crud_spring_webflux.user.domain.models.User;
import com.crud.crud_spring_webflux.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> findAllUsers(ServerRequest request) {
        return request.bodyToMono(User.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .body(userService.findAll(), User.class));
    }

    public Mono<ServerResponse> findUserById(ServerRequest request) {
        UUID publicId = UUID.fromString(request.queryParam("public_id").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findByPublicId(publicId), User.class);
    }

}
