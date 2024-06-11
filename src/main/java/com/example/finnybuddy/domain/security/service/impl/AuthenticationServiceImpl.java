package com.example.finnybuddy.domain.security.service.impl;

import com.example.finnybuddy.domain.security.dto.request.SignUpRequest;
import com.example.finnybuddy.domain.security.dto.request.SigninRequest;
import com.example.finnybuddy.domain.security.dto.response.JwtAuthenticationResponse;
import com.example.finnybuddy.domain.security.service.AuthenticationService;
import com.example.finnybuddy.domain.security.service.JwtService;
import com.example.finnybuddy.domain.user.model.Role;
import com.example.finnybuddy.domain.user.model.User;
import com.example.finnybuddy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        log.info("User signup initiated for email: {}", request.getEmail());
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN).build();
        userRepository.save(user);
        log.info("User with email '{}' successfully signed up", user.getEmail());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        log.info("User signin initiated for email: {}", request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail());
        log.info("User with email '{}' successfully signed in", user.getEmail());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}