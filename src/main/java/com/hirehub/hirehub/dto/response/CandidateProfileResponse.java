package com.hirehub.hirehub.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CandidateProfileResponse {

    private Long id;
    private Long userId;
    private String profilePhoto;
    private String education;
    private String experience;
    private BigDecimal expectedSalary;
    private String skills;
    private String location;
    private String bio;
}
