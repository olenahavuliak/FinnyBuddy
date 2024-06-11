package com.example.finnybuddy.domain.user.service;

import com.example.finnybuddy.domain.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserEntityService {
    UserDetailsService userDetailsService();

    List<UserEntity> getAllUsers();

    Page<UserEntity> getUsersPageable(Pageable pageable);
}