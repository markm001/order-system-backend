package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.exceptions.InvalidIdException;
import com.ccat.ordersys.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}

//@Service
//public class UserDao {
//    List<User> userList = new ArrayList<>();
//    public UserDao() {
//        userList.add(new User(
//                1L,
//                "newuser@user.com",
//                "pass01",
//                "John",
//                "Doe"));
//    }
//    public User findById(Long id) {
//
//        //Check if User with Id exists in DB:
//        User user = userList.stream()
//                .filter(u -> u.getId().equals(id))
//                .findFirst()
//                .orElseThrow(new InvalidIdException(String.format("User with Id %d was not found.",id)));
//
//        return user;
//    }
//}