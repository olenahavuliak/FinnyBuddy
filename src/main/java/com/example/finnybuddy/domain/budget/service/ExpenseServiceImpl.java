package com.example.finnybuddy.domain.budget.service;

import com.example.finnybuddy.core.exceptions.ExceptionMessages;
import com.example.finnybuddy.core.exceptions.NotFoundException;
import com.example.finnybuddy.domain.budget.dto.expense.ExpenseSummaryRequestDTO;
import com.example.finnybuddy.domain.budget.dto.expense.ExpenseSummaryResponseDTO;
import com.example.finnybuddy.domain.budget.mapper.ExpenseMapper;
import com.example.finnybuddy.domain.budget.model.Expense;
import com.example.finnybuddy.domain.budget.model.enumerations.ExpenseCategory;
import com.example.finnybuddy.domain.budget.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    @Override
    public Expense getById(String id) {
        return expenseRepository.findById(id).orElseThrow(() -> new NotFoundException(ExceptionMessages.EXPENSE_NOT_FOUND));
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

    @Override
    public List<Expense> getAllExpensesByCategory(ExpenseCategory category) {
        return expenseRepository.findAllByCategory(category);
    }

    @Override
    public ExpenseSummaryResponseDTO getExpenseSummary(String userId, ExpenseCategory category, LocalDate dueDate) {
        ExpenseSummaryRequestDTO dto = new ExpenseSummaryRequestDTO(userId, category, dueDate);
        List<Expense> expenseList;
        if (Objects.isNull(dto.getCategory()) && Objects.isNull(dto.getDueDate())) {
            expenseList = expenseRepository.findAllByUserId(dto.getUserId());
        }
        else if (Objects.nonNull(dto.getCategory()) && Objects.isNull(dto.getDueDate())) {
            expenseList = expenseRepository.findAllByUserIdAndCategory(dto.getUserId(),dto.getCategory());
        }
        else if(Objects.isNull(dto.getCategory())){
            expenseList = expenseRepository.findAllByUserIdAndPaymentDateBefore(dto.getUserId(), dto.getDueDate());
        }
        else {
            expenseList = expenseRepository.findAllByUserIdAndCategoryAndPaymentDateBefore(dto.getUserId(),dto.getCategory(), dto.getDueDate());
        }

        return mapExpensesToSummary(expenseList);
    }

    private ExpenseSummaryResponseDTO mapExpensesToSummary(List<Expense> expenseList) {
        ExpenseSummaryResponseDTO summary = new ExpenseSummaryResponseDTO();
        Double totalAmount = expenseList.stream().mapToDouble(Expense::getAmount).sum();
        Map<ExpenseCategory, Double> items = new EnumMap<>(ExpenseCategory.class);
        for (Expense expense : expenseList) {
            if(!items.containsKey(expense.getCategory())){
                items.putIfAbsent(expense.getCategory(), expense.getAmount());
            }
            else {
                Double amount = items.get(expense.getCategory());
                amount += expense.getAmount();
                items.replace(expense.getCategory(), amount);
            }
        }

        summary.setTotalExpenseAmount(totalAmount);
        summary.setTotalAmountOfItems(expenseList.size());
        summary.setCategoryItems(items);
        return summary;
    }
}
