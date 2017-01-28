package org.webapp.example.school.data_repository;

import org.webapp.example.school.domain_model.Student;

import java.util.List;

/**
 * Created by Mark on 1/22/2017.
 */
public interface StudentRepository {

    List<Student> getAllStudents();
    Student getStudent(String name);
    void addStudent(Student student);
}
