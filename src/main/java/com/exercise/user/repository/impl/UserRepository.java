package com.exercise.user.repository.impl;

import com.exercise.user.domain.User;
import com.exercise.user.repository.IuserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class UserRepository implements IuserRepository {

    private SessionFactory sessionFactory;

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
    public int save(User user) {
        Serializable id = currentSession().save(user);
        return (int)id;
    }

    @Override
    public User findUserByname(String username) {
        String sql = "SELECT * FROM user where username = :username";
        List users = currentSession().createNativeQuery(sql)
                .addEntity(User.class)
                .setParameter("username", username)
                .list();
        return users.isEmpty() ? null : (User) users.get(0);

    }

    @Override
    public User findUserById(int id) {
        return currentSession().get(User.class, id);
    }

    @Override
    public void deleteUserByid(int id) {
        User user = currentSession().get(User.class, id);
        currentSession().delete(user);
    }

    @Override
    public void updateUser(User user) {
        currentSession().update(user);
    }

    @Override
    public List findAll() {
        String sql = "SELECT * FROM user";
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql);
        nativeQuery.addEntity(User.class);
        return nativeQuery.getResultList();
    }

    @Override
    public List pagingfindUser(int total, int pagesize) {
        String sql = "SELECT * FROM user";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(User.class)
                .setFirstResult(total - 1)
                .setMaxResults(pagesize)
                .list();
        return list;
    }

    public List conditionsQuery(int roleid){
        String sql = "SELECT * FROM user where role_id = :roleid";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(User.class)
                .setParameter("roleid",roleid)
                .list();
        return list;
    }
    public List conditionsQuery(String username){
        String sql = "SELECT * FROM user where username like :username";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(User.class)
                .setParameter("username","%"+username+"%")
                .list();
        return list;
    }


}
