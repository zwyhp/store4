package com.exercise.user.service.impl;

import com.exercise.domain.PageDomain;
import com.exercise.user.domain.Role;
import com.exercise.user.repository.IRoleRepository;
import com.exercise.user.service.IroleService;
import com.exercise.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IroleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public int addObject(Role role) {
        Role roleByname = roleRepository.findRoleByname(role.getName());
        BussinessUtil.isnotNull(roleByname,BussinessUtil.ROLENAME_REPETITION);
        role.setEnable(1);
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        int save = roleRepository.save(role);
        return save;
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
        BussinessUtil.isnotNull(roleRepository.findRoleByname(role.getName()),BussinessUtil.ROLENAME_REPETITION);
        roleById.copy(role);
        roleById.setUpdateTime(LocalDateTime.now());
        roleRepository.updateRoleByid(roleById);
    }

    @Override
    public List findAll() {
        return roleRepository.findAll();
    }

    @Override
    public PageDomain pagingFindAll(int total, int pageSize) {
        int size = roleRepository.findAll().size();
        BussinessUtil.pagingfind((size/pageSize)+1 < total);
        List users = roleRepository.pagingFindRole(total, pageSize);
        return new PageDomain(total, pageSize, size, users);

    }

    @Override
    public Role findObjectById(int id) {
        Role roleById = roleRepository.findRoleById(id);
        BussinessUtil.isNull(roleById,BussinessUtil.ROLE_INEXISTENCE);
        return roleById;
    }

    @Override
    public Role findObjectByName(String name) {
        Role role= roleRepository.findRoleByname(name);
        BussinessUtil.isNull(role,BussinessUtil.ROLE_INEXISTENCE);
        return role;
    }
}
