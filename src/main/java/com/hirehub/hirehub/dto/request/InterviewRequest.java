package com.hirehub.hirehub.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InterviewRequest {

    @NotNull(message = "Job Application ID is required")
    private Long jobApplicationId;

    @NotBlank(message = "Interview type is is required")
    @Size(max = 100, message = "Interview type must not exceed 100 characters")
    private String interviewType;

    @NotNull(message = "Scheduled time is required")
    @Future(message = "Scheduled time must be in the future")
    private LocalDateTime scheduledAt;

    private String meetingLink;

    @Size(max = 50, message = "Status must not be exceed 50 characters")
    private String status;

    @Size(max = 1000, message = "Feedback must not exceed 1000 characters")
    private String feedback;
}
