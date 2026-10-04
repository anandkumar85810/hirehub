package com.hirehub.hirehub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String description;
    private String location;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private Integer experienceRequired;
    private String employmentType;
    private String status;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "job")
    private List<JobApplication> jobApplications;
}
