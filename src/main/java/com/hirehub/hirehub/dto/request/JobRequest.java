package com.hirehub.hirehub.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class JobRequest {

    @NotNull(message = "Company ID is required")
    @Positive(message = "Company ID must be greater than 0")
    private Long companyId;

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must not exceed 150 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location must not exceed 200 characters")
    private String location;

    @NotNull(message = "Minimum salary is required")
    @PositiveOrZero(message = "Minimum salary must be 0 or greater")
    private BigDecimal salaryMin;

    @NotNull(message = "Maximum salary is required")
    @PositiveOrZero(message = "Maximum salary must be 0 or greater")
    private BigDecimal salaryMax;

    @PositiveOrZero(message = "Experience required must be 0 or greater")
    private Integer experienceRequired;

    @NotBlank(message = "EmploymentType is required")
    private String employmentType;

    @NotBlank(message = "status is required")
    private String status;
}
