package com.exercise.event.handler;

import com.exercise.event.eventinterface.DomainEvent;

/**
 * 用于注册 DomainEventHandler 领域事件
 */
public interface DomainEventHandlerRegistry {

    default <E extends DomainEvent>void register(DomainEventSubscriber<E> subscriber, DomainEventHandler<E> handler){
        register(subscriber, new DomainEventExecutor.SyncExecutor(), handler);
    }

    default <E extends DomainEvent>void register(Class<E> eventCls, DomainEventHandler<E> handler){
        register(event -> event.getClass() == eventCls, new DomainEventExecutor.SyncExecutor(), handler);
    }

    default <E extends DomainEvent>void register(Class<E> eventCls, DomainEventExecutor executor, DomainEventHandler<E> handler){
        register(event -> event.getClass() == eventCls, executor, handler);
    }

    <E extends DomainEvent>void register(DomainEventSubscriber<E> subscriber, DomainEventExecutor executor, DomainEventHandler<E> handler);

    <E extends DomainEvent> void unregister(DomainEventSubscriber<E> subscriber);

    <E extends DomainEvent> void unregisterAll(DomainEventHandler<E> handler);
}
