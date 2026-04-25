package com.securebank.api.infrastructure.web.controller;

import com.securebank.api.application.dto.TransactionRequestDTO;
import com.securebank.api.application.dto.TransactionResponseDTO;
import com.securebank.api.application.service.TransactionService;
import com.securebank.api.infrastructure.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> transfer(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody TransactionRequestDTO request) {
        
        Long senderId = userDetails.getUser().getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.transfer(senderId, request));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getMyTransactions(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        
        return ResponseEntity.ok(transactionService.getUserTransactions(userDetails.getUser().getId()));
    }
}
