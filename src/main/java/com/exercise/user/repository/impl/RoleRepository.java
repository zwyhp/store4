package com.exercise.user.repository.impl;

import com.exercise.user.domain.Role;
import com.exercise.user.repository.IRoleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class RoleRepository implements IRoleRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public RoleRepository(SessionFactory sessionFactory) {
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
    public int save(Role role) {
        Serializable id = currentSession().save(role);
        return (int)id;
    }

    @Override
    public Role findRoleByname(String rolename) {
        String sql = "SELECT * FROM srole where rname = :rolename";
        List users = currentSession().createNativeQuery(sql)
                .addEntity(Role.class)
                .setParameter("rolename", rolename)
                .list();
        return users.isEmpty() ? null : (Role) users.get(0);
    }

    @Override
    public Role findRoleById(int id) {
        return currentSession().get(Role.class, id);
    }

    @Override
    public void EnableRoleByid(int id) {
        Role role = currentSession().get(Role.class, id);
        role.setEnable((role.getEnable() == 1) ? 0 : 1);
        updateRoleByid(role);
    }

    @Override
    public void updateRoleByid(Role role) {
        currentSession().update(role);
    }

    @Override
    public List findAll() {
        String sql = "SELECT * FROM srole";
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql);
        nativeQuery.addEntity(Role.class);
        return nativeQuery.getResultList();
    }

    @Override
    public List pagingFindRole(int total, int pagesize) {
        String sql = "SELECT * FROM srole";
        return currentSession().createNativeQuery(sql)
                .addEntity(Role.class)
                .setFirstResult(total - 1)
                .setMaxResults(pagesize)
                .list();
    }


}
