package com.example.finnybuddy.domain.budget.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeSettingsDTO {
    private Integer workingDaysPerMonthCount;
    private Integer workingWeeksPerMonthCount;
    private Integer workingHoursPerWeekCount;
    private Integer nationalHolidaysDaysPerYearCount;
    private Integer vacationDaysPerYearCount;
}
