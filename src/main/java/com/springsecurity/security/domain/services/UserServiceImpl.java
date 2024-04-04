package com.springsecurity.security.domain.services;

import com.springsecurity.security.domain.repositories.IUserRepository;
import com.springsecurity.security.domain.usecases.IUserUseCase;
import com.springsecurity.security.persistence.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserUseCase {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Iterable<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado")));
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return Optional.ofNullable(repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado")));
    }

    @Override
    public UserEntity save(UserEntity newUser) {
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        return repository.save(newUser);
    }

    @Override
    public Iterable<UserEntity> saveAll(Iterable<UserEntity> users) {
        users.forEach(userEntity -> userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword())));
        return repository.saveAll(users);
    }

    @Override
    public void delete(Long id) {
        this.repository.delete(id);
    }
}
