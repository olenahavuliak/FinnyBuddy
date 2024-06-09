package com.example.finnybuddy.domain.budget.dto.expense;

import com.example.finnybuddy.core.validation.annotations.RequiredField;
import com.example.finnybuddy.core.validation.annotations.ValueValidation;
import com.example.finnybuddy.domain.budget.model.enumerations.ExpenseCategory;
import com.example.finnybuddy.domain.budget.model.enumerations.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseRequestDTO {
    @RequiredField
    private String userId;
    @RequiredField
    private String name;
    @RequiredField
    @ValueValidation(min = 0, message = "Amount should be more than 0")
    private Double amount;
    @RequiredField
    private LocalDate paymentDate;
    @RequiredField
    private ExpenseCategory category;
    private PaymentMethod paymentMethod;
    private String currency;
    @RequiredField
    private Boolean recurring;
    private String notes;
}
