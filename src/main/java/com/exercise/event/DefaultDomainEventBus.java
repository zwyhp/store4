package com.exercise.event;


import com.exercise.event.eventinterface.*;
import com.exercise.event.handler.DomainEventExecutor;
import com.exercise.event.handler.DomainEventHandler;
import com.exercise.event.handler.DomainEventSubscriber;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * 发布与注册事件的默认实现
 */

@Qualifier
public class DefaultDomainEventBus implements DomainEventBus {

    private static List<RegisterItem> registerItems;

    static {
        registerItems = Lists.newArrayList();
    }

    public DefaultDomainEventBus() {

    }

    public List<RegisterItem> getRegisterItems() {
        return registerItems;
    }

    @Override
    public <EVENT extends DomainEvent> void publish(EVENT event) {
        registerItems.forEach(registerItem -> registerItem.handEvent(event));
    }

    @Override
    public <E extends DomainEvent> void register(DomainEventSubscriber<E> subscriber, DomainEventExecutor executor, DomainEventHandler<E> handler) {
        registerItems.add(new RegisterItem(subscriber, executor, handler));
    }

    @Override
    public <E extends DomainEvent> void unregister(DomainEventSubscriber<E> subscriber) {
        doUnregister(registerItem -> registerItem.getSubscriber() == subscriber);
    }

    @Override
    public <E extends DomainEvent> void unregisterAll(DomainEventHandler<E> handler) {
        doUnregister(registerItem -> registerItem.getEventHandler() == handler);
    }

    private void doUnregister(Function<RegisterItem, Boolean> checker){
        Iterator<RegisterItem> registerItemIterator = this.registerItems.iterator();
        while (registerItemIterator.hasNext()){
            RegisterItem registerItem = registerItemIterator.next();
            if (checker.apply(registerItem)){
                registerItemIterator.remove();
            }
        }
    }

    @Data
    private class RegisterItem{
        private final DomainEventSubscriber subscriber;
        private final DomainEventExecutor executor;
        private final DomainEventHandler eventHandler;

        public <EVENT extends DomainEvent> void handEvent(EVENT event) {
            if (subscriber.accept(event)){
                executor.submit(eventHandler, event);
            }
        }
    }
}
