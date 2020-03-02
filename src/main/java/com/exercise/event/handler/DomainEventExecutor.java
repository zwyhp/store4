package com.exercise.event.handler;

import com.exercise.event.eventinterface.DomainEvent;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 领域事件的执行者
 */
public interface DomainEventExecutor {
    Logger LOGGER = LoggerFactory.getLogger(DomainEventExecutor.class);

    default <E extends DomainEvent>  void submit(DomainEventHandler<E> handler, E event){
        submit(new Task<>(handler, event));
    }

    <E extends DomainEvent> void submit(Task<E> task);

    @Value
    class Task<E extends DomainEvent> implements Runnable{
        private final DomainEventHandler<E> handler;
        private final E event;

        @Override
        public void run() {
            this.handler.handle(this.event);
        }
    }

    class SyncExecutor implements DomainEventExecutor{
        @Override
        public <E extends DomainEvent> void submit(Task<E> task) {
            task.run();
        }
    }
}
