package com.hirehub.hirehub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "candidate_profiles")
@Getter
@Setter
public class CandidateProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String profilePhoto;
    private String education;
    private String experience;
    private String skills;
    private BigDecimal expectedSalary;
    private String location;

    @Column(length = 1000)
    private String bio;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
