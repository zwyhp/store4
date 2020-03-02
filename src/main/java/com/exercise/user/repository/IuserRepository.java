package com.exercise.user.repository;

import com.exercise.user.domain.User;

import java.util.List;


public interface IuserRepository {
    long count();

    int save(User user);

    User findUserByname(String username);

    User findUserById(int id);

    void deleteUserByid(int id);

    void updateUserByid(User user);

    List findAll();

    List pagingfindUser(int total, int pagesize);

    List conditionsQuery(String username);

    List conditionsQuery(String username, int roleid);
}
