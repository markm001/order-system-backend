package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.OrderItem;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderItemDao {
    List<OrderItem> orderItemList = new ArrayList<>();
    public void save(OrderItem response) {
        orderItemList.add(response);
    }

    public List<OrderItem> findAllByOrderId(List<Long> pendingOrderIds) {
        //Filter all Order-Items that contain the pending Ids:
        return orderItemList.stream()
                .filter(orderItem -> pendingOrderIds.contains(orderItem.getOrderId()))
                .collect(Collectors.toList());
    }
}
