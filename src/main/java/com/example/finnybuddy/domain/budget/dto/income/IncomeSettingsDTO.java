package com.example.finnybuddy.domain.budget.dto.income;

import com.example.finnybuddy.core.validation.annotations.RequiredField;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IncomeSettingsDTO {
    @RequiredField
    private Integer workingDaysPerMonthCount;
    @RequiredField
    private Integer workingWeeksPerMonthCount;
    @RequiredField
    private Integer workingHoursPerWeekCount;
    @RequiredField
    private Integer nationalHolidaysDaysPerYearCount;
    @RequiredField
    private Integer vacationDaysPerYearCount;
}
