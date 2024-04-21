package com.example.finnybuddy.domain.budget.mapper;

import com.example.finnybuddy.core.EntityMapper;
import com.example.finnybuddy.domain.budget.dto.IncomeRequestDTO;
import com.example.finnybuddy.domain.budget.dto.IncomeResponseDTO;
import com.example.finnybuddy.domain.budget.dto.IncomeSettingsDTO;
import com.example.finnybuddy.domain.budget.model.Income;
import com.example.finnybuddy.domain.budget.model.IncomeSettings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class IncomeMapper implements EntityMapper<Income, IncomeResponseDTO, IncomeRequestDTO> {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Income toEntity(IncomeRequestDTO dto);

    public abstract IncomeResponseDTO toDto(Income entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract Income update(Income source, @MappingTarget Income target);

    public List<IncomeResponseDTO> toListDto(List<Income> entityList) {
        List<IncomeResponseDTO> dtoList = new ArrayList<>();
        if (entityList.isEmpty()) {
            return dtoList;
        }
        for (Income entity : entityList) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    public List<Income> toListEntity(List<IncomeRequestDTO> dtoList) {
        List<Income> entityList = new ArrayList<>();
        if (dtoList.isEmpty()) {
            return entityList;
        }
        for (IncomeRequestDTO dto : dtoList) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }

    public abstract IncomeSettingsDTO toDto(IncomeSettings entity);
    public abstract IncomeSettings toEntity(IncomeSettingsDTO incomeSettingsDTO);

    @Mapping(target = "id", ignore = true)
    public abstract IncomeSettings update(IncomeSettings source, @MappingTarget IncomeSettings target);

}
