package com.example.finnybuddy.domain.budget.dto.expense;

import com.example.finnybuddy.core.validation.annotations.RequiredField;
import com.example.finnybuddy.domain.budget.model.enumerations.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ExpenseSummaryRequestDTO {
    @RequiredField
    private String userId;
    private ExpenseCategory category;
    private LocalDate dueDate;
}
