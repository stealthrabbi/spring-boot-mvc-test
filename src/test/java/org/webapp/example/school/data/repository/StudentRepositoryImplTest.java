package org.webapp.example.school.data.repository;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.webapp.example.school.AbstractSpringBootTest;
import org.webapp.example.school.domain.Student;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link StudentRepository}.
 */
@Transactional
public class StudentRepositoryImplTest extends AbstractSpringBootTest {

    @Autowired
    private StudentRepository mStudentRepository;

    @Autowired
    private SessionFactory mSessionFactory;

    /**
     * Clear all records at start of tests.
     */
    @Before
    public void clearData() {
        mSessionFactory.getCurrentSession().createQuery("delete from Student").executeUpdate();
    }

    /**
     * Tests that the list is initially emtpy.
     */
    @Test
    public void listInitiallyEmpty() {
        List<Student> list = mStudentRepository.getAllStudents();
        assertEquals(0, list.size());
    }

    /**
     * Verifies a student can be added and then retrieved.
     */
    @Test
    public void addStudentRecord() {
        mStudentRepository.addStudent(new Student("Mario", new Date(), "aaaa"));

        List<Student> allStudents = mStudentRepository.getAllStudents();

        assertEquals(1, allStudents.size());
        assertEquals("Mario", allStudents.get(0).getName());
    }

    /**
     * Verifies a student can be found by name.
     */
    @Test
    public void findStudentRecordByName() {
        mStudentRepository.addStudent(new Student("Mario", new Date(), "aaaa"));
        mStudentRepository.addStudent(new Student("Luigi", new Date(), "bbbb"));
        mStudentRepository.addStudent(new Student("Toad", new Date(), "bbbb"));
        mStudentRepository.addStudent(new Student("Waluigi", new Date(), "bbbb"));

        List<Student> allStudents = mStudentRepository.getAllStudents();

        assertEquals(4, allStudents.size());

        Student foundStudent = mStudentRepository.getStudent("Toad");
        assertEquals("Toad", foundStudent.getName());
    }

    /**
     * Verifies null is returned if student can't be found by name.
     */
    @Test
    public void findStudentRecordByNameNulLWhenUnfound() {
        mStudentRepository.addStudent(new Student("Mario", new Date(), "aaaa"));
        mStudentRepository.addStudent(new Student("Luigi", new Date(), "bbbb"));
        mStudentRepository.addStudent(new Student("Toad", new Date(), "bbbb"));
        mStudentRepository.addStudent(new Student("Waluigi", new Date(), "bbbb"));

        List<Student> allStudents = mStudentRepository.getAllStudents();

        assertEquals(4, allStudents.size());

        Student foundStudent = mStudentRepository.getStudent("Wario");
        assertNull(foundStudent);
    }
}
