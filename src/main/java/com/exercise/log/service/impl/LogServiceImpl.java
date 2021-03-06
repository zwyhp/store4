package com.exercise.log.service.impl;

import com.exercise.domain.PageDomain;
import com.exercise.log.domain.LogDomin;
import com.exercise.log.repository.IlogRePository;
import com.exercise.log.service.ILogService;
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
        LogDomin log = ilogRePository.findLogById(id);
        BussinessUtil.isNull(log,BussinessUtil.LOG_INEXISTENCE);
        ilogRePository.deleteLogByid(id);
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
    public PageDomain pagingFindAll(int total, int pagesize) {
        int size = ilogRePository.findAll().size();
        List logDomins = ilogRePository.PagingfindLog(total, pagesize);
        return new PageDomain(total,pagesize,size,logDomins);

    }

    @Override
    public LogDomin findObjectById(int id) {
        return ilogRePository.findLogById(id);
    }

    @Override
    public void deleteAllLog() {
        List<LogDomin> all = (List<LogDomin>) findAll();
        for (LogDomin log : all) {
            ilogRePository.deleteLogByid(log.getId());
        }
    }
}
