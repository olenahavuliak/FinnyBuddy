package com.example.finnybuddy.domain.budget.dto.income;

import com.example.finnybuddy.core.validation.annotations.RequiredField;
import com.example.finnybuddy.core.validation.annotations.ValueValidation;
import com.example.finnybuddy.domain.budget.model.enumerations.IncomeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IncomeRequestDTO {
    @RequiredField
    private String userId;
    @RequiredField
    private Boolean isRegular;
    private String notes;
    @RequiredField
    private IncomeType type;
    @RequiredField
    @ValueValidation(min = 0, message = "Amount should be more than 0")
    private Double amount;
    private Double taxes;
    private LocalDate paymentDate;
}
