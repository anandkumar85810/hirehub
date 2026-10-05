package com.hirehub.hirehub.dto.response;

import com.hirehub.hirehub.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private Role role;
    private String status;
    private Long candidateProfileId;
}
