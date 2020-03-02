package com.exercise.event;
import com.exercise.event.eventinterface.*;
import com.exercise.event.eventinterface.Aggregate;
import com.google.common.base.Preconditions;

/**
 * AggregateEvent 实现类，继承子 AbstractDomainEvent，并添加 source 属性
 * 聚合产生的事件的抽象类
 * @param <A>
 */
public abstract class AbstractAggregateEvent<A extends Aggregate >
    extends AbstractDomainEvent
    implements AggregateEvent<A> {
    private final A source;

    public AbstractAggregateEvent(A source) {
        Preconditions.checkArgument(source != null);
        this.source = source;
    }

    public AbstractAggregateEvent(String id, A source){
        super(id);
        this.source = source;
    }

    @Override
    public A source() {
        return this.source;
    }
}
