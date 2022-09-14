package com.ccat.ordersys.model.service;

import com.ccat.ordersys.exceptions.OrderSystemException;
import com.ccat.ordersys.model.entity.Item;
import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.entity.User;
import com.ccat.ordersys.model.repository.ItemDao;
import com.ccat.ordersys.model.repository.OrderDao;
import com.ccat.ordersys.model.repository.OrderItemDao;
import com.ccat.ordersys.model.repository.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public Order createOrder(Order request) throws OrderSystemException {
        //Check User-ID existence in DB, throw Exception:
        Optional<User> user = userDao.findById(request.getUserId());
        if(user.isEmpty()) {
            throw new OrderSystemException(
                    String.format("User with id %d was not found.",request.getUserId()),
                    HttpStatus.BAD_REQUEST);
        }
        return orderDao.save(request);
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
                request.getItemId(),
                request.getQuantity());
        orderItemDao.save(response);
        return response;
    }
}
