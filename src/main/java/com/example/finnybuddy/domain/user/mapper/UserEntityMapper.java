package com.example.finnybuddy.domain.user.mapper;


import com.example.finnybuddy.domain.user.model.UserEntity;
import com.example.finnybuddy.domain.user.dto.UserEntityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    @Mapping(target = "id", source = "userEntity.id")
    @Mapping(target = "email", source = "userEntity.email")
    @Mapping(target = "firstName", source = "userEntity.firstName")
    @Mapping(target = "lastName", source = "userEntity.lastName")
    @Mapping(target = "role", source = "userEntity.role")
    UserEntityDto toUserDTO(UserEntity userEntity);

    @Mapping(target = "id", source = "userEntities.id")
    @Mapping(target = "email", source = "userEntities.email")
    @Mapping(target = "firstName", source = "userEntities.firstName")
    @Mapping(target = "lastName", source = "userEntities.lastName")
    @Mapping(target = "role", source = "userEntities.role")
    List<UserEntityDto> toListDTO(List<UserEntity> userEntities);
}
