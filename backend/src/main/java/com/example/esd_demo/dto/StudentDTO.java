package com.example.esd_demo.dto;

import lombok.Getter;

@Getter
public class StudentDTO {
    private Integer studentId;
    private String rollNumber;
    private String firstName;
    private String lastName;
    private String email;

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}