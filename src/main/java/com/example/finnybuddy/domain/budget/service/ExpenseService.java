package com.example.finnybuddy.domain.budget.service;

import com.example.finnybuddy.core.utils.BasicCrudOperations;
import com.example.finnybuddy.domain.budget.dto.expense.ExpenseSummaryResponseDTO;
import com.example.finnybuddy.domain.budget.model.Expense;
import com.example.finnybuddy.domain.budget.model.enumerations.ExpenseCategory;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService extends BasicCrudOperations<Expense, String> {
    List<Expense> getAllExpensesByCategory(ExpenseCategory category);

    ExpenseSummaryResponseDTO getExpenseSummary(String userId, ExpenseCategory category, LocalDate dueDate);
}
