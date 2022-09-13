package com.ccat.ordersys.model.service;

import com.ccat.ordersys.model.entity.Item;
import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.entity.User;
import com.ccat.ordersys.model.repository.ItemDao;
import com.ccat.ordersys.model.repository.OrderDao;
import com.ccat.ordersys.model.repository.OrderItemDao;
import com.ccat.ordersys.model.repository.UserDao;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    UserDao userDao = new UserDao();
    OrderDao orderDao = new OrderDao();
    OrderItemDao orderItemDao = new OrderItemDao();
    ItemDao itemDao = new ItemDao();

    public Order createOrder(Order request) throws Exception {
        Optional<User> user = userDao.findById(request.getUserId());
        if(user.isEmpty()) {
            //throw Exception if User with requested ID was not found:
            throw new Exception("User was not found.");
        }
        return orderDao.save(request);
    }

    public OrderItem createOrderItem(Long orderId, OrderItem request) throws Exception {

        //Check Item-ID existence in DB, throw Exception:
        Optional<Item> item = itemDao.findById(request.getItemId());
        if(item.isEmpty()) {
            throw new Exception("Item could not be found");
        }
        //Check Order-ID existence in DB, throw Exception:
        Optional<Order> order = orderDao.findById(orderId);
        if(order.isEmpty()) {
            throw new Exception("Order could not be found.");
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
