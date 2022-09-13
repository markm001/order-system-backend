package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderItemDao {
    List<OrderItem> orderItemList = new ArrayList<>();
    public void save(OrderItem response) {
        orderItemList.add(response);
    }
}
