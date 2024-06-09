package com.example.finnybuddy.domain.budget.mapper;

import com.example.finnybuddy.core.EntityMapper;
import com.example.finnybuddy.domain.budget.dto.expense.ExpenseRequestDTO;
import com.example.finnybuddy.domain.budget.dto.expense.ExpenseResponseDTO;
import com.example.finnybuddy.domain.budget.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ExpenseMapper implements EntityMapper<Expense, ExpenseResponseDTO, ExpenseRequestDTO> {

    @Override
    @Mapping(target = "id", ignore = true)
    public abstract Expense toEntity(ExpenseRequestDTO dto);

    @Override
    public List<Expense> toListEntity(List<ExpenseRequestDTO> listDto) {
        List<Expense> entityList = new ArrayList<>();
        if (listDto.isEmpty()) {
            return entityList;
        }
        for (ExpenseRequestDTO dto : listDto) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }

    @Override
    public List<ExpenseResponseDTO> toListDto(List<Expense> entityList) {
        List<ExpenseResponseDTO> dtoList = new ArrayList<>();
        if (entityList.isEmpty()) {
            return dtoList;
        }
        for (Expense entity : entityList) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    @Override
    @Mapping(target = "id", ignore = true)
    public abstract Expense update(Expense entity1, @MappingTarget Expense entity2);
}
