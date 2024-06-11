package com.example.finnybuddy.domain.user.controller;

import com.example.finnybuddy.core.constants.RestEndpoints;
import com.example.finnybuddy.core.utils.PageDTO;
import com.example.finnybuddy.core.utils.PageMapper;
import com.example.finnybuddy.domain.user.dto.UserEntityDto;
import com.example.finnybuddy.domain.user.mapper.UserEntityMapper;
import com.example.finnybuddy.domain.user.service.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestEndpoints.USER_BASE_URL)
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name="UserEntity", description = "UserEntity API. Contains all the operations that can be performed on a user.")
public class UserEntityController {
    private final UserEntityService userEntityService;
    private final UserEntityMapper userEntityMapper;
    private final PageMapper pageMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Fetch list of users")
    @GetMapping("")
    public ResponseEntity<List<UserEntityDto>> getAllUsers(){
        return ResponseEntity.ok(userEntityMapper.toListDTO(userEntityService.getAllUsers()));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Fetch users pageable")
    @GetMapping("/pageable")
    public ResponseEntity<PageDTO<UserEntityDto>> getPagesOfUsers(Pageable pageable){
        return ResponseEntity.ok(pageMapper.toPageDto(userEntityService.getUsersPageable(pageable).map(userEntityMapper::toUserDTO)));
    }

}
