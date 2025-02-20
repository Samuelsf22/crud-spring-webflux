package com.crud.crud_spring_webflux.user.domain.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonIgnore
    private Long id;
    private UUID publicId;
    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    private String roles;

}
