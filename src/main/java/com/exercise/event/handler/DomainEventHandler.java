package com.exercise.event.handler;

import com.exercise.event.eventinterface.DomainEvent;

/**
 * 用于处理领域事件
 */
public interface DomainEventHandler<E extends DomainEvent> {
    void handle(E event);
}
