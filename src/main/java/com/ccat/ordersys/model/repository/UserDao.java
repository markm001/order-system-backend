package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.User;
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
    public Optional<User> findById(Long id) {
        return userList.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
}
