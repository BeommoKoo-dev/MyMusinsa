package org.prgrms.mymusinsa.order.service;

import lombok.AllArgsConstructor;
import org.prgrms.mymusinsa.global.exception.ErrorCode;
import org.prgrms.mymusinsa.global.exception.GlobalCustomException;
import org.prgrms.mymusinsa.order.domain.Order;
import org.prgrms.mymusinsa.order.dto.OrderCreateRequestDTO;
import org.prgrms.mymusinsa.order.dto.OrderResponseDTO;
import org.prgrms.mymusinsa.order.dto.OrderUpdateRequestDTO;
import org.prgrms.mymusinsa.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDTO createOrder(OrderCreateRequestDTO orderCreateRequestDTO) {
        Order order = orderCreateRequestDTO.toOrder();
        orderRepository.insert(order);
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
        order.update(orderUpdateRequestDTO);
        orderRepository.update(order);
    }

    @Transactional
    public void deleteOrderById(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

}
