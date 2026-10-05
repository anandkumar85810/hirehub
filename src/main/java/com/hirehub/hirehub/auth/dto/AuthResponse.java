package com.hirehub.hirehub.auth.dto;

import com.hirehub.hirehub.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String type;
    private Long userId;
    private String username;
    private String email;
    private Role role;
}
