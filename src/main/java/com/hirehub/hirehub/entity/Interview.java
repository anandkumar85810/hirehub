package com.hirehub.hirehub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
@Getter
@Setter
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jobApplication_id", nullable = false)
    private JobApplication jobApplication;

    @Column(nullable = false)
    private String interviewType;

    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    private String meetingLink;

    private String status;

    @Column(length = 1000)
    private String feedback;
}
