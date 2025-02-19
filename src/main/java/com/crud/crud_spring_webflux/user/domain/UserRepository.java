package com.crud.crud_spring_webflux.user.domain;

import com.crud.crud_spring_webflux.user.domain.models.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> findByPublicId(Long publicId);

    Flux<User> findAll();

    Mono<User> findByEmail(String email);

    Mono<Boolean> existsByEmail(String email);

    Mono<Void> deleteByPublicId(Long publicId);

}
