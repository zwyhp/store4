package com.exercise.log.service;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.IService;
import com.exercise.log.domain.LogDomin;

import java.util.List;

public interface ILogService extends IService<LogDomin> {

    @Override
    int addObject(LogDomin logDomin);

    @Override
    void deleteObjectById(int id);

    @Override
    void updateObjectById(LogDomin logDomin);

    @Override
    List<LogDomin> findAll();

    @Override
    PageDomain pagingfindAll(int pagenum, int pagesize);

    @Override
    LogDomin findObjectById(int id);
}
