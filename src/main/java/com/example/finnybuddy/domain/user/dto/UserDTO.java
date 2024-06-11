package com.example.finnybuddy.domain.user.dto;

import com.example.finnybuddy.domain.user.model.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
