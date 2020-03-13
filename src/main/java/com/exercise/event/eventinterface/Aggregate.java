package com.exercise.event.eventinterface;


import com.exercise.event.AbstractAggregateEvent;

/**
 * 聚合的接口
 */
public interface Aggregate  {
     void enable(AbstractAggregateEvent aggregateEvent);
}
