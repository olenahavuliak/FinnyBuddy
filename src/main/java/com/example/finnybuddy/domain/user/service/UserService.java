package com.example.finnybuddy.domain.user.service;

import com.example.finnybuddy.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<User> getAllUsers();

    Page<User> getUsersPageable(Pageable pageable);
}