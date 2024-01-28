package com.example.finnybuddy.core;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface EntityMapper<E, D, R> {
    E toEntity (R dto);
    D toDto (E entity);
    List<E> toListEntity(List<R> listDto);
    List<D> toListDto(List<E> listEntity);
    E update(E entity1, @MappingTarget E entity2);
}
