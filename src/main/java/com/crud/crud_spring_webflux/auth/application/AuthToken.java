package com.crud.crud_spring_webflux.auth.application;

import lombok.Builder;

@Builder
public record AuthToken(String token) {}
