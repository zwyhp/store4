package com.exercise.log.repository;

import com.exercise.log.domin.LogDomin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class LogRepository implements IlogRePository {

    private SessionFactory sessionFactory;

    @Autowired
    public LogRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int save(LogDomin log) {
        Serializable id = currentSession().save(log);
        return (int)id;
    }

    @Override
    public LogDomin findUserByname(String username) {
        String sql = "select * FROM log where u_name = " + username;
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql).addEntity(LogDomin.class);
        return (LogDomin)nativeQuery.list().get(0);
    }

    @Override
    public LogDomin findUserById(int id) {
        String hql = "select * FROM log where id = " + id;
        NativeQuery nativeQuery = currentSession().createNativeQuery(hql).addEntity(LogDomin.class);
        return (LogDomin)nativeQuery.list().get(0);

    }

    @Override
    public void deleteUserByid(int id) {
        LogDomin logDomin = currentSession().get(LogDomin.class, id);
        currentSession().delete(logDomin);
    }

    @Override
    public List findAll() {
        String sql = "SELECT * FROM log";
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql);
        nativeQuery.addEntity(LogDomin.class);
        return nativeQuery.list();
    }

    @Override
    public List PagingfindLog(int total, int pagesize) {
        String sql = "SELECT * FROM log";
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql);
        nativeQuery.addEntity(LogDomin.class);
        nativeQuery.setFirstResult(total);
        nativeQuery.setMaxResults(pagesize);
        return nativeQuery.list();
    }
}
