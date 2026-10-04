package com.hirehub.hirehub.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationRequest {

    @NotNull
    private Long candidateId;

    @NotNull
    private Long jobId;

}
