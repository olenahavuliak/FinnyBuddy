package com.example.finnybuddy.domain.security.service;


import com.example.finnybuddy.domain.security.dto.request.SignUpRequest;
import com.example.finnybuddy.domain.security.dto.request.SigninRequest;
import com.example.finnybuddy.domain.security.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}