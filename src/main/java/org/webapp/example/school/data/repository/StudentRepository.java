package org.webapp.example.school.data.repository;

import org.webapp.example.school.domain.Student;

import java.util.List;

/**
 * Created by Mark on 1/22/2017.
 */
public interface StudentRepository {

    /**
     * Gets all students.
     * @return list of students
     */
    List<Student> getAllStudents();

    /**
     * Gets the specified student.
     * @param name name of student
     * @return the student
     */
    Student getStudent(String name);

    /**
     * Adds a student.
     * @param student the student
     */
    void addStudent(Student student);
}
