package org.prgrms.mymusinsa.order.service;

import lombok.AllArgsConstructor;
import org.prgrms.mymusinsa.global.exception.ErrorCode;
import org.prgrms.mymusinsa.global.exception.GlobalCustomException;
import org.prgrms.mymusinsa.order.domain.Order;
import org.prgrms.mymusinsa.order.domain.OrderItem;
import org.prgrms.mymusinsa.order.dto.OrderCreateRequestDTO;
import org.prgrms.mymusinsa.order.dto.OrderResponseDTO;
import org.prgrms.mymusinsa.order.dto.OrderUpdateRequestDTO;
import org.prgrms.mymusinsa.order.repository.OrderRepository;
import org.prgrms.mymusinsa.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponseDTO createOrder(OrderCreateRequestDTO orderCreateRequestDTO) {
        Order order = orderCreateRequestDTO.toOrder();
        List<OrderItem> orderItems = order.getOrderItems();

        orderRepository.insert(order);
        incrementProductSalesCountOf(orderItems);
        return order.toResponseDTO();
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
            .stream()
            .map(Order::toResponseDTO)
            .toList();
    }

    public OrderResponseDTO getOrderById(UUID orderId) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow(() ->
            new GlobalCustomException(ErrorCode.DB_DATA_NOTFOUND_ERROR));
        return order.toResponseDTO();
    }

    @Transactional
    public void updateOrderById(UUID orderId, OrderUpdateRequestDTO orderUpdateRequestDTO) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow(() ->
            new GlobalCustomException(ErrorCode.DB_DATA_NOTFOUND_ERROR));
        List<OrderItem> orderItems = order.getOrderItems();
        decrementProductSalesCountOf(orderItems);
        order.update(orderUpdateRequestDTO);
        List<OrderItem> updatedOrderItems = order.getOrderItems();
        incrementProductSalesCountOf(updatedOrderItems);
        orderRepository.update(order);
    }

    private void incrementProductSalesCountOf(List<OrderItem> updatedOrderItems) {
        updatedOrderItems.forEach(orderItem -> productRepository.incrementSalesCount(
            orderItem.productId(),
            orderItem.quantity()
        ));
    }

    private void decrementProductSalesCountOf(List<OrderItem> orderItems) {
        orderItems.forEach(orderItem -> productRepository.decrementSalesCount(
            orderItem.productId(),
            orderItem.quantity()
        ));
    }

    @Transactional
    public void deleteOrderById(UUID orderId) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow(() ->
            new GlobalCustomException(ErrorCode.DB_DATA_NOTFOUND_ERROR));
        List<OrderItem> orderItems = order.getOrderItems();
        decrementProductSalesCountOf(orderItems);
        orderRepository.deleteById(orderId);
    }

}
