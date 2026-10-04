package com.hirehub.hirehub.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class JobApplicationResponse {

    private Long id;

    private Long candidateId;

    private String candidateName;

    private Long jobId;

    private String jobTitle;

    private String status;

    private LocalDateTime appliedAt;
}
