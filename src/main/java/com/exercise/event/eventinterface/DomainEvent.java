package com.exercise.event.eventinterface;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 通用领域事件接口
 */
public interface DomainEvent {
    /*唯一标识*/
    String id();


    /*发生时间*/
    LocalDateTime occurredOn();

    default LocalDateTime getCreateTime(){
        return occurredOn();
    }

    /*事件类型*/
    default String type(){
        return getClass().getSimpleName();
    }

    default String getType(){
        return type();
    }
}
