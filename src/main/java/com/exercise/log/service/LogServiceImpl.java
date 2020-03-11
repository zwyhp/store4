package com.exercise.log.service;

import com.exercise.domain.PageDomain;
import com.exercise.log.domain.LogDomin;
import com.exercise.log.repository.IlogRePository;
import com.exercise.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements ILogService {

    @Autowired
    private IlogRePository ilogRePository;

    public void addLog(String action,String username){
        LogDomin logDomin = new LogDomin();
        logDomin.setAction(action);
        logDomin.setuName(username);
        logDomin.setTime(LocalDateTime.now());
        addObject(logDomin);
    }

    @Override
    public int addObject(LogDomin logDomin) {
        return ilogRePository.save(logDomin);
    }

    @Override
    public void deleteObjectById(int id) {
        LogDomin userById = ilogRePository.findUserById(id);
        BussinessUtil.isNull(userById,BussinessUtil.LOG_INEXISTENCE);
        ilogRePository.deleteUserByid(id);
    }

    @Override
    public void updateObjectById(LogDomin logDomin) {
        BussinessUtil.error(BussinessUtil.LOG_ERROR);
    }

    @Override
    public List findAll() {
        return ilogRePository.findAll();
    }

    @Override
    public PageDomain pagingfindAll(int total, int pagesize) {
        int size = ilogRePository.findAll().size();
        List logDomins = ilogRePository.PagingfindLog(total, pagesize);
        return new PageDomain(total,pagesize,size,logDomins);

    }

    @Override
    public LogDomin findObjectById(int id) {
        return ilogRePository.findUserById(id);
    }
}
