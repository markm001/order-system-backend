package com.ccat.ordersys.controller;

import com.ccat.ordersys.model.entity.Item;
import com.ccat.ordersys.model.repository.ItemDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private final ItemDao itemDao;
    public ItemController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }


    //Get List of all Items (optional:?name={name}&tag={tag}):
    @RequestMapping("/items")
    public List<Item> getAllItems(
            @RequestParam(required=false,name="name") String name,
            @RequestParam(required = false,name="tag") String tag) {
        return itemDao.findByCriteria(name, tag);
    }

    //Get Items by Id:
    @RequestMapping("/items/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable Long id) {
        Optional<Item> retrievedItem = itemDao.findById(id);
        if(retrievedItem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(retrievedItem.get());
    }

    //TODO: Add additional Api-Endpoints

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
        itemDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/items")
    public ResponseEntity<Object> createItem(@RequestBody Item request) {
        Item response = itemDao.save(request);
        return ResponseEntity.ok(response);
    }

}
