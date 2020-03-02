package com.exercise.user.service;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.IService;
import com.exercise.user.domain.Role;

import java.util.List;

public interface IroleService extends IService<Role> {
    @Override
    int addObject(Role role);

    @Override
    void deleteObjectById(int id);

    @Override
    void updateObjectById(Role role);

    @Override
    List findAll();

    @Override
    PageDomain pagingfindAll(int total, int pagesize);

    @Override
    Role findObjectById(int id);
}
