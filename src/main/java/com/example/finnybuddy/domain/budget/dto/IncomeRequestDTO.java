package com.example.finnybuddy.domain.budget.dto;

import com.example.finnybuddy.domain.budget.model.enumerations.IncomeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IncomeRequestDTO {
    String userId;
    Boolean isRegular;
    String notes;
    IncomeType type;
    Double amount;
    Double taxes;
    LocalDate regularPaymentDate;
}
