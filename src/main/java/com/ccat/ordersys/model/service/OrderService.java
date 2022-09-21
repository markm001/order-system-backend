package com.ccat.ordersys.model.service;

import com.ccat.ordersys.model.OrderResponse;
import com.ccat.ordersys.model.UserResponse;
import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.entity.OrderStatus;
import com.ccat.ordersys.model.entity.User;
import com.ccat.ordersys.model.repository.OrderDao;
import com.ccat.ordersys.model.repository.UserDao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService {

    private final UserDao userDao;
    private final OrderDao orderDao;

    public OrderService( UserDao userDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
    }

    public OrderResponse getOrderById(Long id) {
        Order order = orderDao.getReferenceById(id);
        User user = userDao.getReferenceById(order.getUserId());

        return mapToOrderResponseEntity(order,user);
    }

    public OrderResponse createOrder(Order request) {
        //Check User-ID existence in DB:
        final User user = userDao.getReferenceById(request.getUserId());

        final Order order = new Order(
                UUID.randomUUID().getMostSignificantBits()&Long.MAX_VALUE,
                request.getUserId(),
                LocalDateTime.now(),
                OrderStatus.PENDING,
                Set.of()
        );

        final Order savedOrder = orderDao.save(order);

        return mapToOrderResponseEntity(savedOrder,user);
    }

    public OrderItem createOrderItem(Long orderId, OrderItem request) {

        Order order = orderDao.getReferenceById(orderId);

        //Return requested Item:
        final OrderItem orderItem = new OrderItem(
                UUID.randomUUID().getMostSignificantBits()&Long.MAX_VALUE,
                request.getItemId(),
                request.getQuantity());

        //Update OrderItems Set:
        Set<OrderItem> updatedOrderItems = Stream
                .concat(order.getOrderItems().stream(),
                Stream.of(orderItem)).collect(Collectors.toSet());

        //Create new Order and save:
        Order updatedOrder = new Order(
                order.getId(),
                order.getUserId(),
                order.getOrderTime(),
                order.getOrderStatus(),
                updatedOrderItems
        );
        orderDao.save(updatedOrder);

        return createOrderItemResponseEntity(orderItem);
    }

    public OrderResponse updateOrder(Order request, Long orderId) {
        final Order retrievedOrder = orderDao.getReferenceById(orderId);

        final Order updatedOrder = new Order(
                retrievedOrder.getId(),
                retrievedOrder.getUserId(),
                retrievedOrder.getOrderTime(),
                request.getOrderStatus() == null ? retrievedOrder.getOrderStatus() : request.getOrderStatus(),
                retrievedOrder.getOrderItems()
        );

        Order savedOrder = orderDao.save(updatedOrder);
        User user = userDao.getReferenceById(savedOrder.getUserId());

        return mapToOrderResponseEntity(savedOrder, user);
    }

    private OrderResponse mapToOrderResponseEntity(Order order, User user) {
        return new OrderResponse(
                order.getId(),
                new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName()
                ),
                order.getOrderTime(),
                order.getOrderStatus(),
                order.getOrderItems()
        );
    }

    static OrderItem createOrderItemResponseEntity(OrderItem savedOrderItem) {
        return new OrderItem(
                savedOrderItem.getId(),
                savedOrderItem.getItemId(),
                savedOrderItem.getQuantity()
        );
    }
}
