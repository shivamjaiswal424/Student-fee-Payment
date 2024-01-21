package com.example.esd_demo.dao;

import com.example.esd_demo.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
    Student findByEmailAndPassword(String email, String password);

}