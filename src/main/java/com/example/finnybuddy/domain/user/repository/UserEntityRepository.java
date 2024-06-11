package com.example.finnybuddy.domain.user.repository;

import com.example.finnybuddy.domain.user.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends MongoRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
