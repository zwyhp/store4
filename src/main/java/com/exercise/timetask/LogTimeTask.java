package com.exercise.timetask;

import com.exercise.log.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogTimeTask {

    @Autowired
    private ILogService logService;

    /**
     * 每周日12点 清空日志
     */
    @Scheduled(cron = "0 0 12 ? * SUN")
    public void deleteLog(){
        logService.deleteAllLog();
    }
}
