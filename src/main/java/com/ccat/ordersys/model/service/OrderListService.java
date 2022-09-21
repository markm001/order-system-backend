package com.ccat.ordersys.model.service;

import com.ccat.ordersys.exceptions.InvalidIdException;
import com.ccat.ordersys.model.entity.Item;
import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.entity.OrderList;
import com.ccat.ordersys.model.repository.ItemDao;
import com.ccat.ordersys.model.repository.OrderDao;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderListService {
    private final OrderDao orderDao;
    private final ItemDao itemDao;

    public OrderListService(OrderDao orderDao, ItemDao itemDao) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
    }

    public OrderList getOrderList(Long userId) {
        //Find all pending Orders for specific User:
        List<Order> pendingOrders = orderDao.findAllByUserIdWhereOrderStatusIsPending(userId);

        //Extract Order-Item List from Orders:
        List<OrderItem> orderItems = pendingOrders.stream()
                .flatMap(i -> i.getOrderItems().stream()) //List of Lists -> List
                .collect(Collectors.toList());

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
