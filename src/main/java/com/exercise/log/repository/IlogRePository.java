package com.exercise.log.repository;

import com.exercise.log.domain.LogDomin;

import java.util.List;

public interface IlogRePository {
    int save(LogDomin log);

    LogDomin findUserByname(String username);

    LogDomin findUserById(int id);

    void deleteUserByid(int id);

    List findAll();

    List PagingfindLog(int total, int pagesize);
}
