package org.webapp.example.school.data.repository;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.webapp.example.school.domain.Student;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A data accessing Student Repository implementation. Uses wired in SessionFacotry from Spring/Hibernate.
 */
@Component
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    private final SessionFactory mSessionFactory;

    /**
     * Constructor.
     */
    @Autowired
    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        mSessionFactory = sessionFactory;
    }

    @Override
    public List<Student> getAllStudents() {
        return mSessionFactory.getCurrentSession().createCriteria(Student.class).list();
    }

    @Override
    public Student getStudent(String name) {
        List studentList = mSessionFactory.getCurrentSession()
                .createCriteria(Student.class)
                .add(Restrictions.like("mName", name))
                .list();

        if (!CollectionUtils.isEmpty(studentList)) {
            return (Student) studentList.get(0);
        }
        return null;
    }

    @Override
    public void addStudent(Student student) {
        mSessionFactory.getCurrentSession().saveOrUpdate(student);
    }
}
