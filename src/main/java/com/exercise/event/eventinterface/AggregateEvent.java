package com.exercise.event.eventinterface;

/**
 * 由聚合发布的通用领域事件接口
 * @param <A>聚合类型
 */
public interface AggregateEvent<A extends Aggregate> extends DomainEvent{
    A source();

    default A getSource(){
        return source();
    }
}
