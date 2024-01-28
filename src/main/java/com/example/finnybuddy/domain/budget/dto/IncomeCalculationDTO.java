package com.example.finnybuddy.domain.budget.dto;

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
    private Double income;
    private LocalDate dueDate;
}
