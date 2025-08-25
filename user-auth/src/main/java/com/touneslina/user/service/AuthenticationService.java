package com.touneslina.user.service;

import com.touneslina.user.authentication.AuthenticationRequest;
import com.touneslina.user.authentication.AuthenticationResponse;
import com.touneslina.user.authentication.RegisterRequest;
import com.touneslina.user.config.JwtService;
import com.touneslina.user.entity.Role;
import com.touneslina.user.entity.UserEntity;
import com.touneslina.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .nom(request.getNom())
                .email(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder() // returns the generated token
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getMotDePasse()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("user not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder() // returns the generated token
                .token(jwtToken)
                .build();

    }
}
