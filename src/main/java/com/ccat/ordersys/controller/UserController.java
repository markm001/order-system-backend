package com.ccat.ordersys.controller;

import com.ccat.ordersys.client.FakerClient;
import com.ccat.ordersys.model.UserResponse;
import com.ccat.ordersys.model.entity.Address;
import com.ccat.ordersys.model.entity.OrderList;
import com.ccat.ordersys.model.entity.User;
import com.ccat.ordersys.model.repository.UserDao;
import com.ccat.ordersys.model.service.OrderListService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserDao userDao;
    private final OrderListService orderListService;
    private final FakerClient fakerClient;
    public UserController(UserDao userDao, OrderListService orderListService, FakerClient fakerClient) {
        this.userDao = userDao;
        this.orderListService = orderListService;
        this.fakerClient = fakerClient;

    }

    //Get User via Id:
    @RequestMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {

        Address userAddress = fakerClient.getAddress();

        User response = userDao.getReferenceById(id);
        return new UserResponse(
                response.getId(),
                response.getEmail(),
                response.getFirstName(),
                response.getLastName(),
                userAddress
        );
    }

    //Get User Order-List:
    @RequestMapping("/users/{id}/orderlist")
    public OrderList getOrderList(@PathVariable Long id) {
        return orderListService.getOrderList(id);
    }
}
