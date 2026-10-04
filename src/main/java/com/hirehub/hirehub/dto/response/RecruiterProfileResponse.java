package com.hirehub.hirehub.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterProfileResponse {

    private Long id;
    private Long userId;
    private String username;
    private String email;
    private Long companyId;
    private String companyName;
    private String designation;
    private String department;
    private String phone;
    private String bio;
}
