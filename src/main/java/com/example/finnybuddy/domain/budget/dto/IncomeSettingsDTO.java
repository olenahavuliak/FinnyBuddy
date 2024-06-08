package com.example.finnybuddy.domain.budget.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IncomeSettingsDTO {
    @NotNull(message = "Value could not be null")
    private Integer workingDaysPerMonthCount;
    @NotNull(message = "Value could not be null")
    private Integer workingWeeksPerMonthCount;
    @NotNull(message = "Value could not be null")
    private Integer workingHoursPerWeekCount;
    @NotNull(message = "Value could not be null")
    private Integer nationalHolidaysDaysPerYearCount;
    @NotNull(message = "Value could not be null")
    private Integer vacationDaysPerYearCount;
}
