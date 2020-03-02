package com.exercise.log.service;

import com.exercise.domain.PageDomain;
import com.exercise.log.domin.LogDomin;
import com.exercise.log.repository.IlogRePository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
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
        if (ilogRePository.findUserById(id) != null) {
            ilogRePository.deleteUserByid(id);
        }
    }

    @Override
    public void updateObjectById(LogDomin logDomin) {
    }

    @Override
    public List findAll() {
        return ilogRePository.findAll();
    }

    @Override
    public PageDomain pagingfindAll(int total, int pagesize) {
        int size = ilogRePository.findAll().size();
        List<LogDomin> logDomins = ilogRePository.PagingfindLog(total, pagesize);
        PageDomain pageDomain = new PageDomain(total,pagesize,size,logDomins);
        return pageDomain;
    }

    @Override
    public LogDomin findObjectById(int id) {
        return ilogRePository.findUserById(id);
    }
}
