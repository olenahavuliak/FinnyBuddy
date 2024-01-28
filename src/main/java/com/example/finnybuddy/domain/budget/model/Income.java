package com.example.finnybuddy.domain.budget.model;

import com.example.finnybuddy.domain.budget.model.enumerations.IncomeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "incomes")
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class Income {
    @Id
    String id;
    String userId;
    Boolean isRegular;
    String notes;
    IncomeType type;
    Double amount;
    Double taxes;
    LocalDateTime createdAt;
    LocalDate regularPaymentDate;
}
