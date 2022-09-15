package com.ccat.ordersys.controller;

import com.ccat.ordersys.exceptions.OrderSystemException;
import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.repository.OrderDao;
import com.ccat.ordersys.model.repository.UserDao;
import com.ccat.ordersys.model.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    //GET all orders for User-Id:
    @GetMapping("/orders/{id}")
    public List<Order> getOrders(@PathVariable(name="id") Long userId) {
        return orderService.findAllOrders(userId);
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

    //Update Order(orderStatus) with Id:
    @PutMapping("/orders/{id}")
    public Order updateOrder(@RequestBody Order request,
                                 @PathVariable(name="id") Long orderId) {
        return orderService.updateOrder(request, orderId);
    }
}
