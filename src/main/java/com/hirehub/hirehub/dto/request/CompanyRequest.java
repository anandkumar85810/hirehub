package com.hirehub.hirehub.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 50, message = "Company name must be between 2 to 50")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email formate")
    private String email;

    @Size(max = 500, message = "Description cannot exceed 500 character")
    private String description;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10,12}$", message = "Phone number must contain 10 to 12 digits")
    private String phone;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Logo is required")
    private String logo;

    private String website;
    private String industry;
}
