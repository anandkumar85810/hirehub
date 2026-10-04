package com.hirehub.hirehub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recruiter_profiles")
@Getter
@Setter
public class RecruiterProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User  user;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    private String designation;
    private String department;
    private String phone;

    @Column(length = 1000)
    private String bio;
}
