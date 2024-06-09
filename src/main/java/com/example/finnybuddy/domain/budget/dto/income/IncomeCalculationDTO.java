package com.example.finnybuddy.domain.budget.dto.income;

import com.example.finnybuddy.core.validation.annotations.RequiredField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeCalculationDTO {
    @RequiredField
    private Double income;
    @RequiredField
    private LocalDate dueDate;
}
