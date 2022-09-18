package com.ccat.ordersys.controller;

import com.ccat.ordersys.exceptions.OrderSystemException;
import com.ccat.ordersys.model.OrderResponse;
import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //GET all orders for User-Id:
    @GetMapping("/orders/{id}")
    public OrderResponse getOrders(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    //Book Order:
    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody Order request) {
        return orderService.createOrder(request);
    }

    //Add OrderItems to Order
    @PostMapping("/orders/{id}/items")
    public OrderItem createOrderItem(
            @PathVariable(name="id") Long orderId,
            @RequestBody OrderItem request) throws OrderSystemException {
        return orderService.createOrderItem(orderId ,request);
    }

    //Update Order(orderStatus) with Id:
    @PutMapping("/orders/{id}")
    public OrderResponse updateOrder(@RequestBody Order request,
                                 @PathVariable(name="id") Long orderId) {
        return orderService.updateOrder(request, orderId);
    }
}
