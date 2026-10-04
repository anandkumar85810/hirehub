package com.hirehub.hirehub.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InterviewResponse {

    private Long id;
    private Long jobApplicationId;
    private String interviewType;
    private LocalDateTime scheduledAt;
    private String meetingLink;
    private String status;
    private String feedback;
}
