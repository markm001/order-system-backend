package com.ccat.ordersys.model.service;

import com.ccat.ordersys.exceptions.InvalidIdException;
import com.ccat.ordersys.exceptions.OrderSystemException;
import com.ccat.ordersys.model.entity.*;
import com.ccat.ordersys.model.repository.ItemDao;
import com.ccat.ordersys.model.repository.OrderDao;
import com.ccat.ordersys.model.repository.OrderItemDao;
import com.ccat.ordersys.model.repository.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderService {

    private final UserDao userDao;
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final ItemDao itemDao;

    public OrderService( UserDao userDao, OrderDao orderDao, OrderItemDao orderItemDao,ItemDao itemDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        this.itemDao = itemDao;
    }

    public List<Order> findAllOrders(Long userId) {
        User user = userDao.getReferenceById(userId);
        return orderDao.findByUserId(userId);
    }

    public Order createOrder(Order request) throws OrderSystemException {
        //Check User-ID existence in DB, throw Exception:
        User user = userDao.getReferenceById(request.getId());

        Order response = new Order(
                UUID.randomUUID().getMostSignificantBits()&Long.MAX_VALUE,
                request.getUserId(),
                LocalDateTime.now(),
                OrderStatus.PENDING,
                Set.of()
        );

        return orderDao.saveOrUpdate(response);
    }

    public OrderItem createOrderItem(Long orderId, OrderItem request) throws OrderSystemException {

        //Check Item-ID existence in DB, throw Exception:
        Optional<Item> item = itemDao.findById(request.getItemId());
        if(item.isEmpty()) {
            throw new OrderSystemException(
                    String.format("Item with id %d, could not be found.",request.getItemId()),
                    HttpStatus.BAD_REQUEST);
        }
        //Check Order-ID existence in DB, throw Exception:
        Optional<Order> order = orderDao.findById(orderId);
        if(order.isEmpty()) {
            throw new OrderSystemException(
                    String.format("Order with id %d, could not be found.", orderId),
                    HttpStatus.BAD_REQUEST);
        }

        //Return requested Item:
        OrderItem response = new OrderItem(
                UUID.randomUUID().getMostSignificantBits()&Long.MAX_VALUE,
                orderId,
                request.getItemId(),
                request.getQuantity());
        orderItemDao.save(response);
        return response;
    }

    public Order updateOrder(Order request, Long orderId) {
        final Order retrievedOrder = orderDao.findById(orderId)
                .orElseThrow(new InvalidIdException(String.format("The requested Order-Id %d doesn't exist.",orderId)));

        final Order newOrder = new Order(
                retrievedOrder.getId(),
                retrievedOrder.getUserId(),
                retrievedOrder.getOrderTime(),
                request.getOrderStatus() == null ? retrievedOrder.getOrderStatus() : request.getOrderStatus(),
                retrievedOrder.getOrderList()
        );

        return orderDao.saveOrUpdate(newOrder);
    }
}
