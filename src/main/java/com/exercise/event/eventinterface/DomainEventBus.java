package com.exercise.event.eventinterface;

import com.exercise.event.handler.DomainEventHandlerRegistry;
import com.exercise.event.handler.DomainEventPublisher;

/**
 * 发布和注册的统一接口
 */
public interface DomainEventBus extends DomainEventPublisher, DomainEventHandlerRegistry {

}
