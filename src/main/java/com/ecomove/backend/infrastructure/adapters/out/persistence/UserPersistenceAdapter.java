package com.ecomove.backend.infrastructure.adapters.out.persistence;

import com.ecomove.backend.domain.model.User;
import com.ecomove.backend.domain.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final JpaUserRepository repository;

    @Override
    public User save(User domain) {
        UserEntity entity = mapToEntity(domain);
        UserEntity saved = repository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(this::mapToDomain);
    }

    private UserEntity mapToEntity(User domain) {
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setNom(domain.getNom());
        entity.setPrenom(domain.getPrenom());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setTel(domain.getTel());
        entity.setActif(domain.isActif());
        entity.setDateInscription(domain.getDateInscription());
        entity.setTypePermis(domain.getTypePermis());
        entity.setEntrepriseId(domain.getEntrepriseId());
        entity.setRole(domain.getRole());
        return entity;
    }

    private User mapToDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .tel(entity.getTel())
                .actif(entity.isActif())
                .dateInscription(entity.getDateInscription())
                .typePermis(entity.getTypePermis())
                .entrepriseId(entity.getEntrepriseId())
                .role(entity.getRole())
                .build();
    }
}
