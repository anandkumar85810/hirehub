package com.hirehub.hirehub.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponse {
    private Long id;
    private String name;
    private String email;
    private String description;
    private String phone;
    private String logo;
    private String industry;
    private String website;
    private String location;
}
