package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderDao {
    Set<Order> ordersList = new HashSet<>();

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
                OrderStatus.ORDERED,
                Set.of()
        );
        ordersList.add(response);
        return response;
    }
}
