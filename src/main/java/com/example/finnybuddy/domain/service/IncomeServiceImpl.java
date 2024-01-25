package com.example.finnybuddy.domain.service;

import com.example.finnybuddy.domain.mapper.IncomeMapper;
import com.example.finnybuddy.domain.model.Income;
import com.example.finnybuddy.domain.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;

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
        return null;
    }

    @Override
    public Income getIncomeById(String id) {
        return incomeRepository.findById(id).orElseThrow();
    }
}
