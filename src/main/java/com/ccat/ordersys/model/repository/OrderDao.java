package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Long> {

    @Query(value = "SELECT o FROM Order o WHERE o.userId = ?1")//?1(first param)
    List<Order> findAllByUserId(Long userId);

    @Query(value = "SELECT o FROM Order o WHERE o.orderStatus = 'PENDING' AND o.userId =:userId")
    List<Order> findAllByUserIdWhereOrderStatusIsPending(Long userId);
}