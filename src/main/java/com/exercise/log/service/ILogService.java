package com.exercise.log.service;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.IService;
import com.exercise.log.domain.LogDomin;

import java.util.List;

public interface ILogService extends IService<LogDomin> {

    void addLog(String action,String username);
    @Override
    int addObject(LogDomin logDomin);

    @Override
    void deleteObjectById(int id);

    @Override
    void updateObjectById(LogDomin logDomin);

    @Override
    List<LogDomin> findAll();

    @Override
    PageDomain pagingFindAll(int pagenum, int pagesize);

    @Override
    LogDomin findObjectById(int id);

    void deleteAllLog();
}
