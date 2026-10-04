package com.hirehub.hirehub.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class JobResponse {

    private Long id;
    private Long companyId;
    private String title;
    private String description;
    private String location;
    private BigDecimal salaryMax;
    private BigDecimal salaryMin;
    private Integer experienceRequired;
    private String employmentType;
    private String status;
}
