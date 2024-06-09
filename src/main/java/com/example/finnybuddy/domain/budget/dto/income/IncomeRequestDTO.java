package com.example.finnybuddy.domain.budget.dto.income;

import com.example.finnybuddy.domain.budget.model.enumerations.IncomeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IncomeRequestDTO {
    private String userId;
    private Boolean isRegular;
    private String notes;
    private IncomeType type;
    private Double amount;
    private Double taxes;
    private LocalDate paymentDate;
}
