package com.crud.crud_spring_webflux.user.domain.models;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private UUID publicId;
    private String name;
    private String username;
    private String password;
    private String roles;

}
