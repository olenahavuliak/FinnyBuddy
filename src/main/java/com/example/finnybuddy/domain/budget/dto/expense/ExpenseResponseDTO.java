package com.example.finnybuddy.domain.budget.dto.expense;

import com.example.finnybuddy.domain.budget.model.enumerations.ExpenseCategory;
import com.example.finnybuddy.domain.budget.model.enumerations.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseResponseDTO {
    private String id;
    private String userId;
    private String name;
    private Double amount;
    private LocalDate paymentDate;
    private ExpenseCategory category;
    private PaymentMethod paymentMethod;
    private String currency;
    private Boolean recurring;
    private String notes;
}
