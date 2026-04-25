package com.securebank.api.application.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private BigDecimal balance;
    private String role;
    private LocalDateTime createdAt;
}
