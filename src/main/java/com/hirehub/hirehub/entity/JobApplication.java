package com.hirehub.hirehub.entity;

import com.hirehub.hirehub.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job_applications",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"candidate_id", "job_id"})
        })
@Getter
@Setter
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private User candidate;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    private LocalDateTime appliedAt;

    @OneToMany(mappedBy = "jobApplication")
    private List<Interview> interviews;
}
