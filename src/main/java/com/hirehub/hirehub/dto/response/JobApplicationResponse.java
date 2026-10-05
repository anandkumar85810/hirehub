package com.hirehub.hirehub.dto.response;

import com.hirehub.hirehub.enums.ApplicationStatus;
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

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}
