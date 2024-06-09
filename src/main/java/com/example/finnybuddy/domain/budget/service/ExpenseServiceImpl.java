package com.example.finnybuddy.domain.budget.service;

import com.example.finnybuddy.domain.budget.mapper.ExpenseMapper;
import com.example.finnybuddy.domain.budget.model.Expense;
import com.example.finnybuddy.domain.budget.repository.ExpenseRepository;
import com.example.finnybuddy.core.exceptions.ExceptionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    @Override
    public Expense getById(String id) {
        return expenseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ExceptionMessages.EXPENSE_NOT_FOUND));
    }

    @Override
    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense update(String id, Expense entity) {
        Expense target = getById(id);
        return expenseRepository.save(expenseMapper.update(entity, target));
    }

    @Override
    public Expense create(Expense entity) {
        return expenseRepository.save(entity);
    }
}
