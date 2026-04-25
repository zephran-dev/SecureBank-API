package com.securebank.api.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {
    @NotNull
    private Long receiverId;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;
}
