package com.hirehub.hirehub.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationRequest {

    @NotNull(message = "Candidate ID is required")
    @Positive(message = "Candidate ID must be greater than 0")
    private Long candidateId;

    @NotNull(message = "Job ID is required")
    @Positive(message = "Job ID must be greater than 0")
    private Long jobId;

}
