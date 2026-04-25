package com.securebank.api.infrastructure.web.controller;

import com.securebank.api.application.dto.LoginRequestDTO;
import com.securebank.api.application.dto.TokenResponseDTO;
import com.securebank.api.application.dto.UserCreateDTO;
import com.securebank.api.application.dto.UserResponseDTO;
import com.securebank.api.application.service.AuthService;
import com.securebank.api.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }
}
