package com.exercise.user.repository;

import com.exercise.user.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class UserRepository implements IuserRepository {

    private SessionFactory sessionFactory;
//    @Autowired
//    private HibernateTemplate hibernateTemplate;
    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public User save(User user) {
        Serializable id = currentSession().save(user);
        return new User((int)id,
                user.getUsername(),
                user.getPassword());
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User deleteByUsername(String username) {
        return null;
    }

    @Override
    public User updateByUsername(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
