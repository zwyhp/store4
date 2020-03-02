package com.exercise.user.repository;



import com.exercise.user.domain.Role;

import java.util.List;

public interface IRoleRepository {
    long count();

    int save(Role role);

    Role findRoleByname(String rolename);

    Role findRoleById(int id);

    void EnableRoleByid(int id);

    void updateRoleByid(Role role);

    List findAll();

    List pagingfindRole(int total, int pagesize);
}
