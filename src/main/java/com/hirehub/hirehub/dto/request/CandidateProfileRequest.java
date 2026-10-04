package com.hirehub.hirehub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CandidateProfileRequest {

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be greater than 0")
    private Long userId;

    private String profilePhoto;

    @NotBlank(message = "Education is required")
    @Size(max = 200, message = "Education must not exceed 200 characters")
    private String education;

    @Size(max = 100, message = "Experience must not exceed 100 characters")
    private String experience;

    @NotBlank(message = "Skills is required")
    @Size(max = 500, message = "Skills must not exceed 500 characters")
    private String skills;

    @NotNull(message = "Expected salary is required")
    @Positive(message = "Expected salary must be greater than 0")
    private BigDecimal expectedSalary;

    @Size(max = 1000, message = "Bio must not exceed 1000 characters")
    private String bio;

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location must not exceed 100 character")
    private String location;
}
