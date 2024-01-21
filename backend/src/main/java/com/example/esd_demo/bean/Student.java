package com.example.esd_demo.bean;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Table(name="Student")
@Entity
public class Student {
    @lombok.Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @lombok.Setter
    @Column(nullable = false, unique = true)
    private String rollNumber;
    @lombok.Setter
    @Column(nullable = false)
    private String firstName;
    @lombok.Setter
    @Column
    private String lastName;
    @lombok.Setter
    @Column(nullable = false, unique = true)
    private String email;
    @lombok.Setter
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Bill> billList;




}