package com.ccat.ordersys.service;

import com.ccat.ordersys.model.entity.Item;
import com.ccat.ordersys.model.entity.OrderItem;
import com.ccat.ordersys.model.repository.ItemDao;
import com.ccat.ordersys.model.repository.OrderDao;
import com.ccat.ordersys.model.service.OrderListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OrderListServiceTest {
    private ItemDao itemDao;
    private OrderListService service;

    @BeforeEach
    public void initTests() {
        this.itemDao = mock(ItemDao.class);
        this.service = new OrderListService(
                mock(OrderDao.class),
                itemDao
        );
    }

    @Test
    public void test_calcTotalForEmptyOrderList_returnShippingCost() {
        //given

        //when
        Long result = service.calcTotal(new ArrayList<>(), 800L);

        //then
        assertThat(result).isEqualTo(800);
    }

    @Test
    public void test_calcTotalForSingleItemOrderList_returnSumOfItemPrice() {
        //given
        Item savedItem = saveItem(1000);
            //define Mock ItemDao method:
        given(itemDao.findById(savedItem.getId())).willReturn(Optional.of(savedItem));

        List<OrderItem> orderItems = new ArrayList<>();
        addOrderItem(savedItem, orderItems, 1);

        //when
        Long result = service.calcTotal(orderItems, 500L);
        //then
        assertThat(result).isEqualTo(1500L);
    }

    @Test
    public void test_calcTotalForTwoItemsOrderList_returnSumOfItemsPrice() {
        //given
        Item savedItem1 = saveItem(2000);
        Item savedItem2 = saveItem(500);

        List<OrderItem> orderItems = new ArrayList<>();
        addOrderItem(savedItem1, orderItems, 1);
        addOrderItem(savedItem2, orderItems, 3);

        given(itemDao.findById(savedItem1.getId())).willReturn(Optional.of(savedItem1));
        given(itemDao.findById(savedItem2.getId())).willReturn(Optional.of(savedItem2));

        //when
        Long result = service.calcTotal(orderItems, 500L);
        //then
        assertThat(result).isEqualTo(4000L);
    }

    @Test
    public void test_calcWithNegativeQuantity_throwsException() {
        //given
        Item savedItem1 = saveItem(2000);
        Item savedItem2 = saveItem(500);

        List<OrderItem> orderItems = new ArrayList<>();
        addOrderItem(savedItem1, orderItems, 1);
        addOrderItem(savedItem2, orderItems, -3);

        given(itemDao.findById(savedItem1.getId())).willReturn(Optional.of(savedItem1));
        given(itemDao.findById(savedItem2.getId())).willReturn(Optional.of(savedItem2));

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            //when
            service.calcTotal(orderItems, 800L);
        });
    }

    @Test
    public void test_calcTotalForNonExistingItem_throwsException() {
        //given
        Item unsavedItem = new Item("","",1000L, new HashSet<>());
        List<OrderItem> orderItems = new ArrayList<>();
        addOrderItem(unsavedItem, orderItems, 1);

        //then
        assertThrows(RuntimeException.class, () -> {
            //when
            Long result = service.calcTotal(orderItems, 800L);
        });
    }

    private void addOrderItem(Item savedItem, List<OrderItem> orderItems, int quantity) {
        orderItems.add(new OrderItem(1L, savedItem.getId(), quantity));
    }

    private Item saveItem(long price) {
        return new Item(
                UUID.randomUUID().getMostSignificantBits()&Long.MAX_VALUE,
                "Test-Item-1",
                "Empty",
                price,
                new HashSet<>());
    }
}
