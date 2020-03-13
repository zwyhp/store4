package com.exercise.event;

import com.exercise.event.eventinterface.DomainEventBus;
import com.exercise.event.handler.DomainEventHandlerRegistry;
import com.exercise.event.handler.DomainEventPublisher;
import com.exercise.orderform.event.CreateOrderEvent;
import com.exercise.orderform.event.CreateOrderEventHandler;
import com.exercise.orderform.event.OrderPaymentEvent;
import com.exercise.orderform.event.OrderPaymentEventHandler;
import org.springframework.stereotype.Component;

/**
 * 使用ThreadLocal 管理事件
 */

@Component
public class DomainEventBusHolder {

    private static final  ThreadLocal<DomainEventBus> THREAD_LOCAL = ThreadLocal.withInitial(DefaultDomainEventBus::new);

    public static DomainEventPublisher getPubliser(){
        return THREAD_LOCAL.get();
    }

    public static DomainEventHandlerRegistry getHandlerRegistry(){
        return THREAD_LOCAL.get();
    }

    public static void clean(){
        THREAD_LOCAL.remove();
    }

    /*@PostConstruct
    public void init() {
        THREAD_LOCAL.get().register(OrderPaymentEvent.class, orderPaymentEventHandler);
        THREAD_LOCAL.get().register(CreateOrderEvent.class, createOrderEventHandler);

    }*/

    static {
        THREAD_LOCAL.get().register(OrderPaymentEvent.class,new OrderPaymentEventHandler());
        THREAD_LOCAL.get().register(CreateOrderEvent.class,new CreateOrderEventHandler());
    }

}

