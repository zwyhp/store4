package com.exercise.event.handler;

import com.exercise.event.eventinterface.DomainEvent;

import java.util.List;

/**
 * 用于发布领域事件
 */
public interface DomainEventPublisher {
    <EVENT extends DomainEvent>void publish(EVENT event);

    default <ID, EVENT extends DomainEvent>void publishAll(List<EVENT> events){
        events.forEach(this::publish);
    }
}
