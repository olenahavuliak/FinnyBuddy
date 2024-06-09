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
    private String id;
    private Boolean isRegular;
    private String userId;
    private String notes;
    private IncomeType type;
    private Double amount;
    private Double taxes;
    private LocalDateTime createdAt;
    private LocalDate paymentDate;
}
