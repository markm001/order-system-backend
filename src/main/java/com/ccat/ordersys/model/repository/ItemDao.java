package com.ccat.ordersys.model.repository;

import com.ccat.ordersys.model.entity.Item;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemDao {

    //TODO: Replace with Database.
    //Init List of Items:
    List<Item> itemList = new ArrayList<>();
    public ItemDao() {
        itemList.add(new Item(1L,"Chocolate Bar","Is a food.",499, Set.of("Food","Sweet")));
        itemList.add(new Item(2L,"Cake Mould","Is a product.",6999, Set.of("Item","Utility")));
        itemList.add(new Item(3L,"Eclair au Chocolate","Is a desert.",1099, Set.of("Food","Desert")));
    }


    public List<Item> findByCriteria(String name, String tag) {
        if(name != null) { //if itemList contains the name String
            return itemList.stream()
                    .filter(i -> i.getName().contains(name))
                    .collect(Collectors.toList());
        }
        else if (tag != null) { //if itemList contains the lowercase Tags
            String lowercaseTag = tag.toLowerCase();

            return itemList.stream()
                    .filter(i -> lowercaseItemTags(i).contains(lowercaseTag))
                    .collect(Collectors.toList());
        }
        else return itemList; //no criteria - find all.
    }

    private List<String> lowercaseItemTags(Item i) {
        return i.getTags().stream()
                .map(t -> t.toLowerCase())
                .collect(Collectors.toList());
    }

    public Optional<Item> findById(Long id) {
        return itemList.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Long id) {
        //Save all but Item to delete
        this.itemList = itemList.stream()
                .filter(i -> !i.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Item save(Item request) {
        //Construct new Item & save to List:
        Item response = new Item(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getTags()
        );

        itemList.add(response);
        return response;
    }
}
