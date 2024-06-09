package com.example.finnybuddy.domain.budget.controller;

import com.example.finnybuddy.core.RestEndpoints;
import com.example.finnybuddy.domain.budget.dto.expense.ExpenseRequestDTO;
import com.example.finnybuddy.domain.budget.dto.expense.ExpenseResponseDTO;
import com.example.finnybuddy.domain.budget.mapper.ExpenseMapper;
import com.example.finnybuddy.domain.budget.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(RestEndpoints.EXPENSE_BASE_URL)
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;

    @GetMapping("/all")
    ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseMapper.toListDto(expenseService.getAll()));
    }

    @GetMapping("/{id}")
    ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable String id) {
        return ResponseEntity.ok(expenseMapper.toDto(expenseService.getById(id)));
    }

    @PostMapping
    ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody ExpenseRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseMapper.toDto(expenseService.create(expenseMapper.toEntity(dto))));
    }

    @PutMapping("/{id}")
    ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable String id, @RequestBody ExpenseRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseMapper.toDto(expenseService.update(id, expenseMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteExpenseById(@PathVariable String id) {
        expenseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
