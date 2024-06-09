package com.example.finnybuddy.domain.budget.repository;

import com.example.finnybuddy.domain.budget.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {
}
