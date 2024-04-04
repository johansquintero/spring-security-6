package com.springsecurity.security.domain.usecases;

import com.springsecurity.security.persistence.entities.UserEntity;

import java.util.Optional;

public interface IUserUseCase {
    Iterable<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsername(String username);

    UserEntity save(UserEntity newUser);

    Iterable<UserEntity> saveAll(Iterable<UserEntity> users);

    void delete(Long id);
}
