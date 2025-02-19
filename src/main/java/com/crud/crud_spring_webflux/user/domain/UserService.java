package com.crud.crud_spring_webflux.user.domain;

import com.crud.crud_spring_webflux.user.domain.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> findAll();

    Mono<User> findByPublicId(Long publicId);

    Mono<User> save(User user);

    Mono<User> update(Long publicId, User user);

    Mono<Void> delete(Long publicId);

}
