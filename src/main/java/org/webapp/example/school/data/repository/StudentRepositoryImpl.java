package org.webapp.example.school.data.repository;

import org.springframework.stereotype.Component;
import org.webapp.example.school.domain.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class StudentRepositoryImpl implements StudentRepository {
    private final ArrayList<Student> mStudentList;

    /**
     * Constructor.
     */
    public StudentRepositoryImpl() {
        mStudentList = new ArrayList<Student>();
        mStudentList.add(new Student("Steve", new Date(), "123-45-6789"));
        mStudentList.add(new Student("Martin Fowler", new Date(), "999-45-6789"));
        mStudentList.add(new Student("Leeroy Jenkins", new GregorianCalendar(2005, 5, 11).getTime(), "000-00-0001"));
    }

    @Override
    public List<Student> getAllStudents() {
        return mStudentList;
    }

    @Override
    public Student getStudent(String name) {
        for (Student student : mStudentList) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void addStudent(Student student) {
        mStudentList.add(student);
    }
}
