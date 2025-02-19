package com.crud.crud_spring_webflux.user.domain;

import java.util.UUID;

import com.crud.crud_spring_webflux.user.domain.models.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> findByPublicId(UUID publicId);

    Flux<User> findAll();

    Mono<User> findByEmail(String email);

    Mono<Boolean> existsByEmail(String email);

    Mono<User> save(User user);

    Mono<Void> deleteByPublicId(UUID publicId);

}
