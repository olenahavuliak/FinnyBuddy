package com.example.finnybuddy.domain.dto;

import com.example.finnybuddy.domain.model.enumerations.IncomeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class IncomeResponseDTO {
    String id;
    String userId;
    Boolean isRegular;
    String notes;
    IncomeType type;
    Double amount;
    Double taxes;
    LocalDateTime currentPaymentDate;
    LocalDate regularPaymentDate;
}
