package org.prgrms.mymusinsa.order.repository;

import org.prgrms.mymusinsa.order.domain.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    Order insert(Order order);

    int updateById(UUID orderId, Order order);

    void deleteById(UUID orderId);

    List<Order> findAll();

    Order findOrderById(UUID orderId);

}
