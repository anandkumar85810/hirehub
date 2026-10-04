package com.hirehub.hirehub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterProfileRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long companyId;

    @Size(max = 100)
    private String designation;

    @Size(max = 100)
    private String department;

    @Size(max = 15)
    private String phone;

    @Size(max = 1000)
    private String bio;
}
