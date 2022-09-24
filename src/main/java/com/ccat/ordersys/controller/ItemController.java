package com.ccat.ordersys.controller;

import com.ccat.ordersys.exceptions.InvalidIdException;
import com.ccat.ordersys.model.entity.Item;
import com.ccat.ordersys.model.repository.ItemDao;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        List<Item> allItems = tag == null ? itemDao.findAll() : itemDao.findByTag(tag.toLowerCase());


        return allItems.stream()
                .filter(i -> name == null || i.getName().contains(name))
                .map(i -> createItemResponseEntity(i))
                .collect(Collectors.toList());
    }

    //Get Items by Id:
    @RequestMapping("/items/{id}")
    public Item getItemById(@PathVariable Long id) {
        Item retrievedItem = itemDao.getReferenceById(id);

        return createItemResponseEntity(retrievedItem);
    }

    @CacheEvict(value = "itemsByTag", allEntries = true)
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
        itemDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CacheEvict(value = "itemsByTag", allEntries = true)
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
        Item savedItem = itemDao.save(response);

        return createItemResponseEntity(savedItem);
    }

    @CacheEvict(value = "itemsByTag", allEntries = true)
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

        Item savedItem = itemDao.save(updatedItem);

        return createItemResponseEntity(savedItem);
    }

    private Item createItemResponseEntity(Item item) {
        return new Item(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getTags());
    }

    private List<String> lowercaseItemTags(Item i) {
        return i.getTags().stream()
                .map(t -> t.toLowerCase())
                .collect(Collectors.toList());
    }
}
