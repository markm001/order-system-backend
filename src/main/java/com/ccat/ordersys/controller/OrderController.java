package com.ccat.ordersys.controller;

import com.ccat.ordersys.exceptions.OrderSystemException;
import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.repository.OrderDao;
import com.ccat.ordersys.model.repository.UserDao;
import com.ccat.ordersys.model.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Book Order:
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order request) throws OrderSystemException {
        return orderService.createOrder(request);
    }

    //Add OrderItems to Order
    @PostMapping("/orders/{id}/items")
    public OrderItem createOrderItem(
            @PathVariable(name="id") Long orderId,
            @RequestBody OrderItem request) throws OrderSystemException {
        return orderService.createOrderItem(orderId ,request);
    }
}
