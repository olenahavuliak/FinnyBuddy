package com.example.finnybuddy.domain.budget.repository;

import com.example.finnybuddy.domain.budget.model.Expense;
import com.example.finnybuddy.domain.budget.model.enumerations.ExpenseCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findAllByCategory(ExpenseCategory category);
    List<Expense> findAllByUserId(String userId);
    List<Expense> findAllByUserIdAndCategory(String userId, ExpenseCategory category);
    List<Expense> findAllByUserIdAndPaymentDateBefore(String userId, LocalDate date);
    List<Expense> findAllByUserIdAndCategoryAndPaymentDateBefore(String userId,ExpenseCategory category, LocalDate date);
}
