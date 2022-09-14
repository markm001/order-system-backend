package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.exceptions.OrderSystemException;
import com.ccat.ordersys.model.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDao {
    List<User> userList = new ArrayList<>();
    public UserDao() {
        userList.add(new User(
                1L,
                "newuser@user.com",
                "pass01",
                "John",
                "Doe"));
    }
    public Optional<User> findById(Long id) throws OrderSystemException {

        //Check if User with Id exists in DB - throw Exception
        Optional<User> user = userList.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        if (user.isEmpty()) throw new OrderSystemException(
                String.format("User with requested id %d could not be found", id), HttpStatus.BAD_REQUEST);

        return user;
    }
}
