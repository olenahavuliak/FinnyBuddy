package com.example.finnybuddy.domain.mapper;

import com.example.finnybuddy.domain.dto.IncomeRequestDTO;
import com.example.finnybuddy.domain.dto.IncomeResponseDTO;
import com.example.finnybuddy.domain.model.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class IncomeMapper {
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

    public List<Income> toEntityDto(List<IncomeRequestDTO> dtoList) {
        List<Income> entityList = new ArrayList<>();
        if (dtoList.isEmpty()) {
            return entityList;
        }
        for (IncomeRequestDTO dto : dtoList) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }

}
