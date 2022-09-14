package com.ccat.ordersys.model.service;

import com.ccat.ordersys.exceptions.InvalidIdException;
import com.ccat.ordersys.exceptions.OrderSystemException;
import com.ccat.ordersys.model.entity.Item;
import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.entity.OrderList;
import com.ccat.ordersys.model.repository.ItemDao;
import com.ccat.ordersys.model.repository.OrderDao;
import com.ccat.ordersys.model.repository.OrderItemDao;
import com.ccat.ordersys.model.repository.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderListService {
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final ItemDao itemDao;

    public OrderListService(OrderDao orderDao, OrderItemDao orderItemDao, ItemDao itemDao) {
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        this.itemDao = itemDao;
    }

    public OrderList getOrderList(Long userId) {
        //Find all pending Orders for specific User:
        List<Order> pendingOrders = orderDao.findAllByUserIdWhereOrderStatusIsPending(userId);

        //Map List of Order Ids:
        List<Long> pendingOrderIds = pendingOrders.stream()
                .map(Order::getId)
                .collect(Collectors.toList());

        //Find pending Order IDs:
        List<OrderItem> orderItems = orderItemDao.findAllByOrderId(pendingOrderIds);

        //Calculate Total Sum + Shipping:
        Long shippingCost = 800L; //TODO: Shipping cost selector!
        Long totalCost = calcTotal(orderItems,shippingCost);

        return new OrderList(
                userId,
                orderItems,
                shippingCost,
                "STANDARD",
                totalCost);
    }

    public Long calcTotal(List<OrderItem> orderItems, Long shippingCost) {
        long itemsSum = orderItems.stream()
                .map(oI -> {
                    Item item = getItems(oI.getItemId());
                    if(oI.getQuantity() <= 0) {
                        throw new IllegalArgumentException(
                                String.format("Order with negative Quantity %d is not allowed.",oI.getQuantity()));
                    }
                    return item.getPrice() * oI.getQuantity();
                })
                .mapToLong(Long::longValue).sum();
        return itemsSum + shippingCost;
    }
    private Item getItems(Long itemId) {
        Optional<Item> item = itemDao.findById(itemId);
        return item.orElseThrow(new InvalidIdException(String.format("Requested Item with ID %d was not found",itemId)));
    }
}
