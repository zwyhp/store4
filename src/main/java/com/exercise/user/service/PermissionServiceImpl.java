package com.exercise.user.service;

import com.exercise.domain.PageDomain;
import com.exercise.user.domain.Permission;
import com.exercise.user.repository.IPermissionRepository;
import com.exercise.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionRepository permissionRepository;
    @Override
    public int addObject(Permission permission) {
        Permission permissionByPer = permissionRepository.findPermissionByPer(permission.getPermission());
        BussinessUtil.isNull(permissionByPer,BussinessUtil.PERNAME_REPETITION);
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
        permission.setUpdateTime(LocalDateTime.now());
        permissionRepository.updatePermission(permission);
    }

    @Override
    public List findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public PageDomain pagingfindAll(int total, int pagesize) {
        int size = permissionRepository.findAll().size();
        List users = permissionRepository.pagingfindPer(total, pagesize);
        return new PageDomain(total, pagesize, size, users);

    }

    @Override
    public Permission findObjectById(int id) {
        Permission permissionByPer = permissionRepository.findPermissionById(id);
        BussinessUtil.isNull(permissionByPer,BussinessUtil.PER_INEXISTENCE);
        return permissionByPer;
    }
}
