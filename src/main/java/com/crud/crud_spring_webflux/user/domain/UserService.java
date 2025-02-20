package com.crud.crud_spring_webflux.user.domain;

import java.util.UUID;

import com.crud.crud_spring_webflux.user.domain.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> findAll();

    Mono<User> findByPublicId(UUID publicId);

    Mono<User> update(UUID publicId, User user);

    Mono<Void> delete(UUID publicId);

}
