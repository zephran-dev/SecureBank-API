package com.securebank.api.application.service;

import com.securebank.api.application.dto.LoginRequestDTO;
import com.securebank.api.application.dto.TokenResponseDTO;
import com.securebank.api.domain.exception.BusinessException;
import com.securebank.api.domain.repository.UserRepository;
import com.securebank.api.infrastructure.security.CustomUserDetails;
import com.securebank.api.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public TokenResponseDTO login(LoginRequestDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new BusinessException("Invalid email or password");
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("User not found"));

        var userDetails = new CustomUserDetails(user);
        var jwtToken = jwtService.generateToken(userDetails);

        return TokenResponseDTO.builder()
                .token(jwtToken)
                .type("Bearer")
                .expiresIn(3600000L) // 1 hour
                .build();
    }
}
