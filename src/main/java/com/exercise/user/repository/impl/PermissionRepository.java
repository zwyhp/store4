package com.exercise.user.repository.impl;


import com.exercise.user.domain.Permission;
import com.exercise.user.repository.IPermissionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class PermissionRepository implements IPermissionRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public PermissionRepository(SessionFactory sessionFactory) {
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
    public int save(Permission permission) {
        Serializable id = currentSession().save(permission);
        return (int)id;
    }

    @Override
    public Permission findPermissionByPer(String permissionname) {
        String sql = "SELECT * FROM permission where permission = :permission";
        List users = currentSession().createNativeQuery(sql)
                .addEntity(Permission.class)
                .setParameter("permission", permissionname)
                .list();
        return users.isEmpty() ? null : (Permission) users.get(0);
    }

    @Override
    public Permission findPermissionById(int id) {
        return currentSession().get(Permission.class, id);
    }

    @Override
    public void deletePermissionByid(int id) {
        Permission permission = currentSession().get(Permission.class, id);
        currentSession().delete(permission);
    }

    @Override
    public void updatePermission(Permission permission) {
        currentSession().update(permission);
    }

    @Override
    public List findAll() {
        String sql = "SELECT * FROM permission";
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql);
        nativeQuery.addEntity(Permission.class);
        return nativeQuery.getResultList();
    }

    @Override
    public List pagingfindPer(int total, int pagesize) {
        String sql = "SELECT * FROM permission";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(Permission.class)
                .setFirstResult(total - 1)
                .setMaxResults(pagesize)
                .list();
        return list;
    }
    public List findPermissionByRoleId(int id){
        String sql = "SELECT * FROM permission where role_id = :roleid";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(Permission.class)
                .setParameter("roleid",id)
                .list();
        return list;
    }

}
