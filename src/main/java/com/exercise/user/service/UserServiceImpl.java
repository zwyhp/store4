package com.exercise.user.service;

import com.exercise.domain.PageDomain;
import com.exercise.user.domain.User;
import com.exercise.user.repository.IuserRepository;
import com.exercise.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements IuserService {
    @Autowired
    private IuserRepository iuserRepository;

    @Override
    public int addObject(User user) {
        User userByname = iuserRepository.findUserByname(user.getUsername());
        BussinessUtil.isNull(userByname,BussinessUtil.USERNAME_REPETITION);
        user.setCreatetime(LocalDateTime.now());
        int id = iuserRepository.save(user);
        return id;
    }

    @Override
    public void deleteObjectById(int id) {
        User userByname = iuserRepository.findUserById(id);
        BussinessUtil.isNull(userByname,BussinessUtil.USER_INEXISTENCE);
        iuserRepository.deleteUserByid(id);
    }

    @Override
    public void updateObjectById(User user) {
        User userByname = iuserRepository.findUserById(user.getId());
        BussinessUtil.isNull(userByname,BussinessUtil.USER_INEXISTENCE);
        iuserRepository.updateUserByid(user);
    }

    @Override
    public List findAll() {
        return iuserRepository.findAll();
    }

    @Override
    public PageDomain pagingfindAll(int total, int pagesize) {
        int size = iuserRepository.findAll().size();
        List users = iuserRepository.pagingfindUser(total, pagesize);
        return new PageDomain(total, pagesize, size, users);
    }

    @Override
    public User findObjectById(int id) {
        User user = iuserRepository.findUserById(id);
        BussinessUtil.isNull(user,BussinessUtil.USER_INEXISTENCE);
        return user;
    }

    public List conditionsQuery(String username){
        return iuserRepository.conditionsQuery(username);
    }

    public List conditionsQuery(String username ,int roleid){
        return iuserRepository.conditionsQuery(username,roleid);
    }


}
