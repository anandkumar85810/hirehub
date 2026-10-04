package com.hirehub.hirehub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String logo;

    @Column(nullable = false)
    private String description;

    private String website;
    private String industry;
    private String location;

    @Column(nullable = false)
    private String phone;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "company")
    private List<Job> jobs;
}
