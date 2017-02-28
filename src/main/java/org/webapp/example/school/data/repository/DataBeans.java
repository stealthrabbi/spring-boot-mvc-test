package org.webapp.example.school.data.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * A class to hold necessary data access beans.
 */
@Configuration
public class DataBeans {

    /**
     * This bean needs to be wired in so that a SessionFactory can be wired in to other data access beans.
     *
     * @return a session factory bean
     */
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
}
