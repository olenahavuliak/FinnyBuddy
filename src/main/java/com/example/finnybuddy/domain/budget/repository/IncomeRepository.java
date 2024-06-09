package com.example.finnybuddy.domain.budget.repository;

import com.example.finnybuddy.domain.budget.model.Income;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IncomeRepository extends MongoRepository<Income, String> {
    List<Income> findAllByUserId(String userId);
}
