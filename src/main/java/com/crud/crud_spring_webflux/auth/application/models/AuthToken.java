package com.crud.crud_spring_webflux.auth.application.models;

import lombok.Builder;

@Builder
public record AuthToken(String token) {}
