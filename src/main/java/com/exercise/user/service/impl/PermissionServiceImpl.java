package com.exercise.user.service.impl;

import com.exercise.domain.PageDomain;
import com.exercise.user.domain.Permission;
import com.exercise.user.domain.Role;
import com.exercise.user.repository.IPermissionRepository;
import com.exercise.user.repository.IRoleRepository;
import com.exercise.user.service.IpermissionService;
import com.exercise.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IpermissionService {
    @Autowired
    private IPermissionRepository permissionRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public int addObject(Permission permission) {
        Permission permissionByPer = permissionRepository.findPermissionByPer(permission.getPermission());
        BussinessUtil.isnotNull(permissionByPer,BussinessUtil.PERNAME_REPETITION);
        permission.setAddTime(LocalDateTime.now());
        permission.setUpdateTime(LocalDateTime.now());
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteObjectById(int id) {
        Permission permissionByPer = permissionRepository.findPermissionById(id);
        BussinessUtil.isNull(permissionByPer,BussinessUtil.PER_INEXISTENCE);
        permissionRepository.deletePermissionByid(id);
    }

    @Override
    public void updateObjectById(Permission permission) {
        Permission permissionByPer = permissionRepository.findPermissionById(permission.getId());
        BussinessUtil.isNull(permissionByPer,BussinessUtil.PER_INEXISTENCE);
        if (!permissionByPer.getPermission().equals(permission.getPermission())) {
            BussinessUtil.isnotNull(permissionRepository.findPermissionByPer(permission.getPermission()), BussinessUtil.PERNAME_REPETITION);
        }
        permissionByPer.copy(permission);
        permissionByPer.setUpdateTime(LocalDateTime.now());
        permissionRepository.updatePermission(permissionByPer);
    }

    @Override
    public List findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public PageDomain pagingFindAll(int total, int pagesize) {
        int size = permissionRepository.findAll().size();
        BussinessUtil.pagingfind((size/pagesize)+1 < total);
        List users = permissionRepository.pagingfindPer(total, pagesize);
        return new PageDomain(total, pagesize, size, users);
    }

    @Override
    public Permission findObjectById(int id) {
        Permission permissionByPer = permissionRepository.findPermissionById(id);
        BussinessUtil.isNull(permissionByPer,BussinessUtil.PER_INEXISTENCE);
        return permissionByPer;
    }

    @Override
    public List<Permission> findPermissionByRoleId(int id){
        List<Permission> permissions = (List<Permission>)permissionRepository.findPermissionByRoleId(id);
        return permissions;
    }

    @Override
    public void bindRole(int id, int rid) {
        Permission permission = permissionRepository.findPermissionById(id);
        BussinessUtil.isNull(permission,BussinessUtil.USER_INEXISTENCE);
        Role role = roleRepository.findRoleById(rid);
        BussinessUtil.isNull(role,BussinessUtil.ROLE_INEXISTENCE);
        permission.setRoleId(rid);
        updateObjectById(permission);
    }
}
