package com.securebank.api.infrastructure.persistence.repository;

import com.securebank.api.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByDocument(String document);
    boolean existsByEmail(String email);
    boolean existsByDocument(String document);
}
