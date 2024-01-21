package com.example.esd_demo.controller;

import com.example.esd_demo.bean.Bill;
import com.example.esd_demo.bean.Student;
import com.example.esd_demo.dto.StudentDTO;
import com.example.esd_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<StudentDTO> login(@RequestBody Student student) {
        Student loggedInStudent = studentService.login(student);
        if (loggedInStudent != null) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentId(loggedInStudent.getStudentId());
            studentDTO.setRollNumber(loggedInStudent.getRollNumber());
            studentDTO.setFirstName(loggedInStudent.getFirstName());
            studentDTO.setLastName(loggedInStudent.getLastName());
            studentDTO.setEmail(loggedInStudent.getEmail());

            // Get bills for the logged-in student


            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}