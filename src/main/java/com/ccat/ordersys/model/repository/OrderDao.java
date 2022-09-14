package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderDao {
    private Set<Order> ordersList = new HashSet<>();

    //Find Order by Id:
    public Optional<Order> findById(Long orderId) {
        return ordersList.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst();
    }

    //Save booked Order:
    public Order save(Order request) {
        Order response = new Order(
                UUID.randomUUID().getMostSignificantBits()&Long.MAX_VALUE,
                request.getUserId(),
                LocalDateTime.now(),
                OrderStatus.PENDING,
                Set.of()
        );
        ordersList.add(response);
        return response;
    }

    public List<Order> findAllByUserIdWhereOrderStatusIsPending(Long userId) {
        //Filter all Orders by UserId & Pending Status
        return ordersList.stream()
                .filter(o -> o.getUserId().equals(userId)
                        && o.getOrderStatus().equals(OrderStatus.PENDING))
                .collect(Collectors.toList());
    }
}
