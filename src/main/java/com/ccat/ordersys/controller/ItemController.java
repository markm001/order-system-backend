package com.ccat.ordersys.controller;

import com.ccat.ordersys.exceptions.InvalidIdException;
import com.ccat.ordersys.model.entity.Item;
import com.ccat.ordersys.model.repository.ItemDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Item createItem(@RequestBody Item request) {
        //Construct new Item & save to List:
        Item response = new Item(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getTags()
        );
        return itemDao.saveOrUpdate(response);
    }

    @PutMapping("/items/{id}")
    public Item updateItem(@RequestBody Item request,
                           @PathVariable(name="id") Long itemId) {
        //for empty fields in request -> Parser sets null
        final Item originalItem =
                itemDao.findById(itemId)
                        .orElseThrow(new InvalidIdException(String.format("Requested Item-Id %d not found.",itemId)));

        //Create new Item, check if request-Fields are null -> write origin or request Fields :: *Immutability achieved!*
        final Item updatedItem = new Item(
                    originalItem.getId(),
                    request.getName() == null ? originalItem.getName() : request.getName(),
                    request.getDescription() == null ? originalItem.getDescription() : request.getDescription(),
                    request.getPrice() == null ? originalItem.getPrice() : request.getPrice(),
                    originalItem.getTags()
            );

        return itemDao.saveOrUpdate(updatedItem);
    }
}
