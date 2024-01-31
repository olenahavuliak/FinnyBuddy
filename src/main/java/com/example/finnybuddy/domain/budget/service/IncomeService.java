package com.example.finnybuddy.domain.budget.service;

import com.example.finnybuddy.domain.budget.dto.IncomeCalculationDTO;
import com.example.finnybuddy.domain.budget.model.Income;
import com.example.finnybuddy.domain.budget.model.IncomeSettings;

import java.time.LocalDate;
import java.util.List;

public interface IncomeService {
    List<Income> getAllIncomes();

    Income createIncome(Income entity);

    Income updateIncome(String id, Income entity);

    void deleteIncome(String id);

    List<Income> getAllIncomesByUserId(String userId);

    Income getIncomeById(String id);

    IncomeCalculationDTO calculateIncomeDueDate(String userId, LocalDate date);

    IncomeSettings updateIncomeSettings(IncomeSettings entity);
}
