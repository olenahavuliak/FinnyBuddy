package com.example.finnybuddy.domain.budget.model;

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
