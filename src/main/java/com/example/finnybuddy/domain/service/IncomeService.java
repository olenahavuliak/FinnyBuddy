package com.example.finnybuddy.domain.service;

import com.example.finnybuddy.domain.model.Income;

import java.util.List;

public interface IncomeService {
    List<Income> getAllIncomes();

    Income createIncome(Income entity);

    Income updateIncome(String id, Income entity);

    void deleteIncome(String id);

    List<Income> getAllIncomesByUserId(String userId);

    Income getIncomeById(String id);
}
