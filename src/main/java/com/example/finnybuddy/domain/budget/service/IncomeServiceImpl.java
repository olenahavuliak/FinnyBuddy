package com.example.finnybuddy.domain.budget.service;

import com.example.finnybuddy.domain.budget.dto.IncomeCalculationDTO;
import com.example.finnybuddy.domain.budget.mapper.IncomeMapper;
import com.example.finnybuddy.domain.budget.model.Income;
import com.example.finnybuddy.domain.budget.model.IncomeSettings;
import com.example.finnybuddy.domain.budget.model.enumerations.IncomeType;
import com.example.finnybuddy.domain.budget.repository.IncomeRepository;
import com.example.finnybuddy.domain.budget.repository.IncomeSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.MONTHS;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;
    private final IncomeSettingsRepository incomeSettingsRepository;

    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public Income createIncome(Income entity) {
        return incomeRepository.save(entity);
    }

    @Override
    public Income updateIncome(String id, Income entity) {
        Income targetIncome = getIncomeById(id);
        return incomeRepository.save(incomeMapper.update(entity, targetIncome));
    }

    @Override
    public void deleteIncome(String id) {
        incomeRepository.delete(getIncomeById(id));
    }

    @Override
    public List<Income> getAllIncomesByUserId(String userId) {
        return incomeRepository.findAllByUserId(userId);
    }

    @Override
    public Income getIncomeById(String id) {
        return incomeRepository.findById(id).orElseThrow();
    }

    @Override
    public IncomeCalculationDTO calculateIncomeDueDate(String userId, LocalDate date) {
        IncomeSettings incomeSettings = incomeSettingsRepository.findFirst();
        List<Income> regularUserIncomes = getAllIncomesByUserId(userId).stream().filter(x -> !x.getType().equals(IncomeType.IRREGULAR)).toList();

        double incomeAmountPerMonth = 0.0;
        incomeAmountPerMonth += getIncomesByType(regularUserIncomes, IncomeType.MONTHLY);
        incomeAmountPerMonth += getIncomesByType(regularUserIncomes, IncomeType.WEEKLY) * incomeSettings.getWorkingWeeksPerMonthCount();
        incomeAmountPerMonth += getIncomesByType(regularUserIncomes, IncomeType.TWICE_PER_MONTH) * (incomeSettings.getWorkingWeeksPerMonthCount() / 2.0);
        incomeAmountPerMonth += getIncomesByType(regularUserIncomes, IncomeType.DAILY) * incomeSettings.getWorkingDaysPerMonthCount();

        long differenceBetweenDates = MONTHS.between(LocalDate.now(), date);

        return new IncomeCalculationDTO(incomeAmountPerMonth * differenceBetweenDates, date);
    }

    @Override
    public IncomeSettings updateIncomeSettings(IncomeSettings entity) {
        IncomeSettings incomeSettings = incomeSettingsRepository.findFirst();
        return incomeSettingsRepository.save(incomeMapper.update(entity, incomeSettings));
    }

    private double getIncomesByType(List<Income> incomes, IncomeType type) {
        return incomes.stream().filter(x -> x.getType().equals(type)).mapToDouble(Income::getAmount).sum();
    }

}
