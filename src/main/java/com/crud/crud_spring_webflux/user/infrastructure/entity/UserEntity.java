package com.crud.crud_spring_webflux.user.infrastructure.entity;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.crud.crud_spring_webflux.user.domain.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "api_user")
public class UserEntity implements UserDetails {

    @Id
    @JsonIgnore
    private Long id;

    @JsonProperty("public_id")
    private UUID publicId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String username;

    @JsonIgnore
    private String password;

    private String roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(roles.split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static UserEntity fromUserEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .publicId(user.getPublicId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

    public User toUser(){
        return User.builder()
                .id(id)
                .publicId(publicId)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }

}
