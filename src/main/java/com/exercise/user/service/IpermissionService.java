package com.exercise.user.service;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.IService;
import com.exercise.user.domain.Permission;

import java.util.List;

public interface IpermissionService extends IService<Permission> {
    @Override
    int addObject(Permission permission);

    @Override
    void deleteObjectById(int id);

    @Override
    void updateObjectById(Permission permission);

    @Override
    List findAll();

    @Override
    PageDomain pagingFindAll(int total, int pagesize);

    @Override
    Permission findObjectById(int id);

    List<Permission> findPermissionByRoleId(int id);
}
