package com.exercise.event;


import com.exercise.event.eventinterface.DomainEvent;
import com.exercise.util.UUIDUtil;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * DomainEvent 实现类，维护 id 和 创建时间
 */
public abstract class AbstractDomainEvent implements DomainEvent {
    private final String id;
    private final LocalDateTime createTime;

    protected AbstractDomainEvent(String id) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(id));
        this.id = id;
        this.createTime =  LocalDateTime.now();
    }

    protected AbstractDomainEvent(){
        this(UUIDUtil.genUUID());
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public LocalDateTime occurredOn() {
        return this.createTime;
    }

}
