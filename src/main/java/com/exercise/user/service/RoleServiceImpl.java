package com.exercise.user.service;

import com.exercise.domain.PageDomain;
import com.exercise.user.domain.Role;
import com.exercise.user.repository.IRoleRepository;
import com.exercise.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class RoleServiceImpl implements IroleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public int addObject(Role role) {
        Role roleByname = roleRepository.findRoleByname(role.getName());
        BussinessUtil.isNull(roleByname,BussinessUtil.ROLENAME_REPETITION);
        role.setEnable(1);
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        return roleRepository.save(role);
    }

    @Override
    public void deleteObjectById(int id) {
        Role roleById = roleRepository.findRoleById(id);
        BussinessUtil.isNull(roleById,BussinessUtil.ROLE_INEXISTENCE);
        /*这里删除角色是关闭使用*/
        roleRepository.EnableRoleByid(id);
    }

    @Override
    public void updateObjectById(Role role) {
        Role roleById = roleRepository.findRoleById(role.getId());
        BussinessUtil.isNull(roleById,BussinessUtil.ROLE_INEXISTENCE);
        roleRepository.updateRoleByid(role);
    }

    @Override
    public List findAll() {
        return roleRepository.findAll();
    }

    @Override
    public PageDomain pagingfindAll(int total, int pagesize) {
        int size = roleRepository.findAll().size();
        List users = roleRepository.pagingfindRole(total, pagesize);
        return new PageDomain(total, pagesize, size, users);

    }

    @Override
    public Role findObjectById(int id) {
        Role roleById = roleRepository.findRoleById(id);
        BussinessUtil.isNull(roleById,BussinessUtil.ROLE_INEXISTENCE);
        return roleById;
    }
}
