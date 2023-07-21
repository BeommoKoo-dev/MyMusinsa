package org.prgrms.mymusinsa.order.repository;

import org.prgrms.mymusinsa.order.domain.Order;
import org.prgrms.mymusinsa.order.domain.OrderItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Order insert(Order order);

    void update(Order order);

    void deleteById(UUID orderId);

    List<Order> findAll();

    Optional<Order> findOrderById(UUID orderId);

}
