package com.exercise.user.repository;

import com.exercise.user.domain.Permission;
import java.util.List;

public interface IPermissionRepository {
    long count();

    int save(Permission permission);

    Permission findPermissionByPer(String permissionname);

    Permission findPermissionById(int id);

    void deletePermissionByid(int id);

    void updatePermission(Permission permission);

    List<Permission> findAll();

    List pagingfindPer(int total, int pagesize);

    List findPermissionByRoleId(int id);
}
