package com.exercise.event;

import com.exercise.event.eventinterface.DomainEventBus;
import com.exercise.event.handler.DomainEventHandlerRegistry;
import com.exercise.event.handler.DomainEventPublisher;
import com.exercise.orderform.event.OrderPaymentEvent;
import com.exercise.orderform.event.OrderPaymentEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 使用ThreadLocal 管理事件
 */

@Component
public class DomainEventBusHolder {
    @Autowired
    private OrderPaymentEventHandler orderPaymentEventHandler;

    private static final  ThreadLocal<DomainEventBus> THREAD_LOCAL = new ThreadLocal<DomainEventBus>(){
        @Override
        protected DomainEventBus initialValue() {
            return new DefaultDomainEventBus();
        }
    };

    public static DomainEventPublisher getPubliser(){
        return THREAD_LOCAL.get();
    }

    public static DomainEventHandlerRegistry getHandlerRegistry(){
        return THREAD_LOCAL.get();
    }

    public static void clean(){
        THREAD_LOCAL.remove();
    }

    @PostConstruct
    public void init() {
        THREAD_LOCAL.get().register(OrderPaymentEvent.class,orderPaymentEventHandler);
    }
}

