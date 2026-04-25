package com.securebank.api.infrastructure.persistence.mapper;

import com.securebank.api.domain.model.User;
import com.securebank.api.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .document(entity.getDocument())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .balance(entity.getBalance())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;
        return UserEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .document(domain.getDocument())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .balance(domain.getBalance())
                .role(domain.getRole())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
