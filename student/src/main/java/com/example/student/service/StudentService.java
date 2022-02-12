package com.example.student.service;

import com.example.student.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void save(Student student);

    List<Student> findAll();

    Optional<Student> findById(Long id);

    void deleteById(Long id);
}
