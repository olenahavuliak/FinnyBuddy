package com.example.finnybuddy.domain.budget.service;

import com.example.finnybuddy.exceptions.ExceptionMessages;
import com.example.finnybuddy.domain.budget.constants.IncomeSettingsDefaultValues;
import com.example.finnybuddy.domain.budget.dto.income.IncomeCalculationDTO;
import com.example.finnybuddy.domain.budget.mapper.IncomeMapper;
import com.example.finnybuddy.domain.budget.model.Income;
import com.example.finnybuddy.domain.budget.model.IncomeSettings;
import com.example.finnybuddy.domain.budget.model.enumerations.IncomeType;
import com.example.finnybuddy.domain.budget.repository.IncomeRepository;
import com.example.finnybuddy.domain.budget.repository.IncomeSettingsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
        IncomeSettings incomeSettings = getIncomeSettings();
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
        IncomeSettings incomeSettings = getIncomeSettings();
        return incomeSettingsRepository.save(incomeMapper.update(entity, incomeSettings));
    }

    @Override
    public IncomeSettings getIncomeSettings() {
        return incomeSettingsRepository.findAll().stream().findFirst().orElseThrow(() -> new IllegalArgumentException(ExceptionMessages.INCOME_SETTINGS_NOT_FOUND));
    }

    private double getIncomesByType(List<Income> incomes, IncomeType type) {
        return incomes.stream().filter(x -> x.getType().equals(type)).mapToDouble(Income::getAmount).sum();
    }

    @PostConstruct
    private void createDefaultIncomeSettings() {
        IncomeSettings settings = incomeSettingsRepository.findAll().stream().findFirst().orElse(null);
        if(Objects.isNull(settings)) {
            IncomeSettings incomeSettings = new IncomeSettings();
            incomeSettings.setWorkingDaysPerMonthCount(IncomeSettingsDefaultValues.DEFAULT_WORKING_DAYS_PER_MONTH_COUNT);
            incomeSettings.setWorkingHoursPerWeekCount(IncomeSettingsDefaultValues.DEFAULT_WORKING_HOURS_PER_WEEK_COUNT);
            incomeSettings.setWorkingWeeksPerMonthCount(IncomeSettingsDefaultValues.DEFAULT_WORKING_WEEKS_PER_MONTH_COUNT);
            incomeSettings.setNationalHolidaysDaysPerYearCount(IncomeSettingsDefaultValues.DEFAULT_NATIONAL_HOLIDAYS_DAYS_PER_YEAR_COUNT);
            incomeSettings.setVacationDaysPerYearCount(IncomeSettingsDefaultValues.DEFAULT_VACATION_DAYS_PER_YEAR_COUNT);
            incomeSettingsRepository.save(incomeSettings);
        }
    }
}
