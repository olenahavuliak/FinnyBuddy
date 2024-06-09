package com.example.finnybuddy.domain.budget.dto.expense;

import com.example.finnybuddy.domain.budget.model.enumerations.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseSummaryResponseDTO {
    private Double totalExpenseAmount;
    private int totalAmountOfItems;
    private Map<ExpenseCategory, Double> categoryItems;
}
