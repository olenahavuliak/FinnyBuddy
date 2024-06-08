package com.example.finnybuddy.domain.budget.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "income_settings")
@FieldNameConstants
@Validated
public class IncomeSettings {
    @Id
    private String id;
    private Integer workingDaysPerMonthCount;
    private Integer workingWeeksPerMonthCount;
    private Integer workingHoursPerWeekCount;
    private Integer nationalHolidaysDaysPerYearCount;
    private Integer vacationDaysPerYearCount;
}
