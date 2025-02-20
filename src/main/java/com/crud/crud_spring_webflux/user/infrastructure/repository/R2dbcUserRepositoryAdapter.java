package com.crud.crud_spring_webflux.user.infrastructure.repository;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.crud.crud_spring_webflux.user.domain.UserRepository;
import com.crud.crud_spring_webflux.user.domain.models.User;
import com.crud.crud_spring_webflux.user.infrastructure.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class R2dbcUserRepositoryAdapter implements UserRepository{

    private final R2dbcUserRepository r2dbcUserRepository;

    @Override
    public Mono<User> findByPublicId(UUID publicId) {
        return r2dbcUserRepository.findByPublicId(publicId).map(UserEntity::toUser);
    }

    @Override
    public Flux<User> findAll() {
        return r2dbcUserRepository.findAll().map(UserEntity::toUser);
    }

    @Override
    public Mono<User> findByUsername(String email) {
        return r2dbcUserRepository.findByUsername(email).map(UserEntity::toUser);
    }

    @Override
    public Mono<Boolean> existsByUsername(String email) {
        return r2dbcUserRepository.findByUsername(email).hasElement();
    }

    @Override
    public Mono<User> save(User user) {
        return r2dbcUserRepository.save(UserEntity.fromUserEntity(user)).map(UserEntity::toUser);
    }

    @Override
    public Mono<Void> deleteByPublicId(UUID publicId) {
        return r2dbcUserRepository.deleteByPublicId(publicId);
    }
    
}
