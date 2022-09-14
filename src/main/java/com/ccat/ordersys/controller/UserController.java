package com.ccat.ordersys.controller;

import com.ccat.ordersys.exceptions.OrderSystemException;
import com.ccat.ordersys.model.entity.OrderList;
import com.ccat.ordersys.model.entity.User;
import com.ccat.ordersys.model.repository.UserDao;
import com.ccat.ordersys.model.service.OrderListService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
public class UserController {

    private final UserDao userDao;
    private final OrderListService orderListService;
    public UserController(UserDao userDao, OrderListService orderListService) {
        this.userDao = userDao;
        this.orderListService = orderListService;
    }

    //Get User via Id:
    @RequestMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) throws OrderSystemException {
        return userDao.findById(id);
    }

    //Get User Order-List:
    @RequestMapping("/users/{id}/orderlist")
    public OrderList getOrderList(@PathVariable Long id) {
        return orderListService.getOrderList(id);
    }
}
