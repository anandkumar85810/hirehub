package com.hirehub.hirehub.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String role;
    private String status;
    private Long candidateProfileId;
}
