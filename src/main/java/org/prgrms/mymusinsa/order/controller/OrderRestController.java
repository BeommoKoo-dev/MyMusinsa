package org.prgrms.mymusinsa.order.controller;

import lombok.RequiredArgsConstructor;
import org.prgrms.mymusinsa.order.dto.OrderCreateRequestDTO;
import org.prgrms.mymusinsa.order.dto.OrderResponseDTO;
import org.prgrms.mymusinsa.order.dto.OrderUpdateRequestDTO;
import org.prgrms.mymusinsa.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("api/v1/orders")
@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> allOrders = orderService.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @PostMapping("/new")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderCreateRequestDTO orderCreateRequestDTO) {
        return ResponseEntity.ok(orderService.createOrder(orderCreateRequestDTO));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity updateOrderById(@PathVariable("orderId")UUID orderId, @RequestBody OrderUpdateRequestDTO orderUpdateRequestDTO) {
        orderService.updateOrderById(orderId, orderUpdateRequestDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteOrderById(@PathVariable("orderId") UUID orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
