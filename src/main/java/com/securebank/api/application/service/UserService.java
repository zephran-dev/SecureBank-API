package com.securebank.api.application.service;

import com.securebank.api.application.dto.UserCreateDTO;
import com.securebank.api.application.dto.UserResponseDTO;
import com.securebank.api.domain.exception.BusinessException;
import com.securebank.api.domain.exception.NotFoundException;
import com.securebank.api.domain.model.Role;
import com.securebank.api.domain.model.User;
import com.securebank.api.domain.repository.UserRepository;
import com.securebank.api.infrastructure.cryptography.AESEncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AESEncryptionUtil aesEncryptionUtil;

    @Transactional
    public UserResponseDTO createUser(UserCreateDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email already in use");
        }
        
        String encryptedDocument = aesEncryptionUtil.encrypt(dto.getDocument());
        if (userRepository.existsByDocument(encryptedDocument)) {
            throw new BusinessException("Document already in use");
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .document(encryptedDocument)
                .password(passwordEncoder.encode(dto.getPassword()))
                .balance(BigDecimal.ZERO)
                .role(Role.USER) // default role
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return mapToResponse(user);
    }

    private UserResponseDTO mapToResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .balance(user.getBalance())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
