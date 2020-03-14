package com.exercise.log.repository;

import com.exercise.log.domain.LogDomin;

import java.util.List;

public interface IlogRePository {
    int save(LogDomin log);

    LogDomin findLogByname(String username);

    LogDomin findLogById(int id);

    void deleteLogByid(int id);

    List findAll();

    List PagingfindLog(int total, int pagesize);
}
