package org.webapp.example.school.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webapp.example.school.domain.Student;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class to initialize the DB with some default data.
 */
@Component
public class DataInitializer {

    /**
     * Initialize the database with some default data.
     * @param studentRepository the student repository
     */
    @Autowired
    public void initialize(StudentRepository studentRepository) {
        studentRepository.addStudent(new Student("Steve", new Date(), "123-45-6789"));
        studentRepository.addStudent(new Student("Martin Fowler", new Date(), "999-45-6789"));
        studentRepository.addStudent(new Student("Leeroy Jenkins",
                new GregorianCalendar(2005, 5, 11).getTime(), "000-00-0001"));

    }
}
