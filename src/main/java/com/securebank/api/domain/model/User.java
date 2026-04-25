package com.securebank.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String document; // encrypted
    private String email;
    private String password;
    private BigDecimal balance;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
