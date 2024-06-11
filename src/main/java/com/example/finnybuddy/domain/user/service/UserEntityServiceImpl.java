package com.example.finnybuddy.domain.user.service;

import com.example.finnybuddy.domain.user.model.UserEntity;
import com.example.finnybuddy.domain.user.mapper.UserEntityMapper;
import com.example.finnybuddy.domain.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {
    private final UserEntityRepository userEntityRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userEntityRepository.findByEmail(username);
            }
        };
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userEntityRepository.findAll();
    }

    @Override
    public Page<UserEntity> getUsersPageable(Pageable pageable) {
        return userEntityRepository.findAll(pageable);
    }

}
