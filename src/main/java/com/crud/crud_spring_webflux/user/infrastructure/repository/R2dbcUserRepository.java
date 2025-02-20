package com.crud.crud_spring_webflux.user.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.crud.crud_spring_webflux.user.infrastructure.entity.UserEntity;

import reactor.core.publisher.Mono;

public interface R2dbcUserRepository extends R2dbcRepository<UserEntity, Long> {

    Mono<UserEntity> findByPublicId(UUID publicId);

    Mono<UserEntity> findByUsername(String username);

    Mono<Void> deleteByPublicId(UUID publicId);
    
}
