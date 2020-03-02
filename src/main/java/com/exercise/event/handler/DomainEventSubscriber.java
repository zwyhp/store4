package com.exercise.event.handler;


import com.exercise.event.eventinterface.DomainEvent;

/**
 * 用于判断是否接受领域事件
 */
public interface DomainEventSubscriber<E extends DomainEvent> {
    boolean accept(E e);
}
