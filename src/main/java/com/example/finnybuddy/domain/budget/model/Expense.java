package com.example.finnybuddy.domain.budget.model;

import com.example.finnybuddy.core.validation.annotations.RequiredField;
import com.example.finnybuddy.domain.budget.model.enumerations.ExpenseCategory;
import com.example.finnybuddy.domain.budget.model.enumerations.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Getter
@Setter
@Document(collection = "expenses")
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    private String id;
    @RequiredField
    private String userId;
    @RequiredField
    private String name;
    @RequiredField
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
