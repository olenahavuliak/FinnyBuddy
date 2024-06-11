package com.example.finnybuddy.domain.user.controller;

import com.example.finnybuddy.core.constants.RestEndpoints;
import com.example.finnybuddy.core.utils.PageDTO;
import com.example.finnybuddy.core.utils.PageMapper;
import com.example.finnybuddy.domain.user.dto.UserDTO;
import com.example.finnybuddy.domain.user.mapper.UserMapper;
import com.example.finnybuddy.domain.user.service.UserService;
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
@Tag(name="User", description = "User API. Contains all the operations that can be performed on a user.")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PageMapper pageMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Fetch list of users")
    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userMapper.toListDTO(userService.getAllUsers()));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Fetch users pageable")
    @GetMapping("/pageable")
    public ResponseEntity<PageDTO<UserDTO>> getPagesOfUsers(Pageable pageable){
        return ResponseEntity.ok(pageMapper.toPageDto(userService.getUsersPageable(pageable).map(userMapper::toUserDTO)));
    }

}
