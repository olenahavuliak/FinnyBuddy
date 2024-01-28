package com.example.finnybuddy.domain.budget.service;

import com.example.finnybuddy.domain.budget.dto.IncomeCalculationDTO;
import com.example.finnybuddy.domain.budget.mapper.IncomeMapper;
import com.example.finnybuddy.domain.budget.model.Income;
import com.example.finnybuddy.domain.budget.model.enumerations.IncomeType;
import com.example.finnybuddy.domain.budget.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MONTHS;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;

    //TODO - add logic to handle working days per month
    private static final Integer WORKING_DAYS_PER_MONTH = 20;

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
        List<Income> regularUserIncomes = getAllIncomesByUserId(userId).stream().filter(Income::getIsRegular).toList();

        double incomeAmountPerMonth = 0.0;
        incomeAmountPerMonth += getIncomesByType(regularUserIncomes, IncomeType.MONTHLY);
        incomeAmountPerMonth += getIncomesByType(regularUserIncomes, IncomeType.WEEKLY)*4;
        incomeAmountPerMonth += getIncomesByType(regularUserIncomes, IncomeType.TWICE_PER_MONTH)*2;
        incomeAmountPerMonth += getIncomesByType(regularUserIncomes, IncomeType.DAILY)*WORKING_DAYS_PER_MONTH;

        long differenceBetweenDates = MONTHS.between(LocalDate.now(), date);

        return new IncomeCalculationDTO(incomeAmountPerMonth * differenceBetweenDates, date);
    }

    private double getIncomesByType(List<Income> incomes, IncomeType type){
        return incomes.stream().filter(x-> x.getType().equals(type)).mapToDouble(Income::getAmount).sum();
    }

}
