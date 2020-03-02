package com.exercise.user.service;

import com.exercise.domain.PageDomain;
import com.exercise.user.domain.User;
import com.exercise.user.repository.IuserRepository;
import com.exercise.util.BussinessExceptionUtil;
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
        BussinessExceptionUtil.isNull(userByname,"用户名重复");
        user.setCreatetime(LocalDateTime.now());
        int i = iuserRepository.save(user);
        return i;
    }

    @Override
    public void deleteObjectById(int id) {
        User userByname = iuserRepository.findUserById(id);
        BussinessExceptionUtil.isNull(userByname,"删除的用户不存在");
        iuserRepository.deleteUserByid(id);
    }

    @Override
    public void updateObjectById(User user) {
        User userByname = iuserRepository.findUserById(user.getId());
        BussinessExceptionUtil.isNull(userByname,"更改的用户不存在");
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
        PageDomain pageDomain = new PageDomain(total, pagesize, size, users);
        return pageDomain;
    }

    @Override
    public User findObjectById(int id) {
        User user = iuserRepository.findUserById(id);
        BussinessExceptionUtil.isNull(user,"查找的用户不存在");
        return user;
    }

    public List conditionsQuery(String username){
        return iuserRepository.conditionsQuery(username);
    }

    public List conditionsQuery(String username ,int roleid){
        return iuserRepository.conditionsQuery(username,roleid);
    }


}
