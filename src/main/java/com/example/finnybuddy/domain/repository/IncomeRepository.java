package com.example.finnybuddy.domain.repository;

import com.example.finnybuddy.domain.model.Income;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncomeRepository extends MongoRepository<Income, String> {
}
