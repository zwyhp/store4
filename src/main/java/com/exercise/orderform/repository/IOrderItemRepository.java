package com.exercise.orderform.repository;



import com.exercise.orderform.domain.Orderitem;

import java.util.List;


public interface IOrderItemRepository {
    int save(Orderitem orderitem);

    void deleteOrderitemByOid(int oId);

    List findOrderitemByOid(int oId);

}
