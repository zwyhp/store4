package com.exercise.interfaceI;

import com.exercise.domain.PageDomain;

import java.util.List;


public interface IService<E> {
    int addObject(E e);
    void deleteObjectById(int id);
    void updateObjectById(E e);
    List findAll();
    PageDomain pagingFindAll(int total, int pagesize);
    E findObjectById(int id);

}
