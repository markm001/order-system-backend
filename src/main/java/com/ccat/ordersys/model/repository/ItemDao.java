package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<Item, Long> {
}