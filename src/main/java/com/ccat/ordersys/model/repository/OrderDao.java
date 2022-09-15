package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderStatus;
import org.springframework.stereotype.Service;

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
    public Order saveOrUpdate(Order response) {
        //if Id exist - Update Order:
        if(findById(response.getId()).isPresent()) {
            ordersList  = ordersList.stream()
                    .map(o -> o.getId().equals(response.getId()) ? response : o)
                    .collect(Collectors.toSet());
        } else ordersList.add(response); //create new Order
        return response;
    }

    public List<Order> findAllByUserIdWhereOrderStatusIsPending(Long userId) {
        //Filter all Orders by UserId & Pending Status
        return ordersList.stream()
                .filter(o -> o.getUserId().equals(userId)
                        && o.getOrderStatus().equals(OrderStatus.PENDING))
                .collect(Collectors.toList());
    }

    public List<Order> findByUserId(Long userId) {
        return ordersList.stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
