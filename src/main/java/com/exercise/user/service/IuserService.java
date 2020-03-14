package com.exercise.user.service;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.IService;
import com.exercise.user.domain.User;
import java.util.List;

public interface IuserService extends IService<User> {
     @Override
     int addObject(User user);

     @Override
     void deleteObjectById(int id);

     @Override
     void updateObjectById(User user);

     @Override
     List findAll();

     @Override
     PageDomain pagingFindAll(int pagenum, int pagesize);

     @Override
     User findObjectById(int id);

     List conditionsQuery(String username);

     List conditionsQuery(int roleid);

     User findUserByname(String username);

    void allotRole(int id, int rid);

}
