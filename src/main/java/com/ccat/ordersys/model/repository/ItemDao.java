package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.Item;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<Item, Long> {

    @Cacheable("itemsByTag")
    @Query(value = "SELECT i FROM Item i JOIN FETCH i.tags tg WHERE tg=:tag")
    List<Item> findByTag(String tag);
}