package com.ccat.ordersys.repository;

import com.ccat.ordersys.model.entity.Order;
import com.ccat.ordersys.model.entity.OrderStatus;
import com.ccat.ordersys.model.entity.User;
import com.ccat.ordersys.model.repository.OrderDao;
import com.ccat.ordersys.model.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties")
public class OrderRepositoryTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @BeforeEach
    public void initTests() {
        userDao.save(new User(
                42L,
                "empty@email.com",
                "emptyPass",
                "Empty",
                "User"));

        userDao.save(new User(
                1L,
                "wrong@email.com",
                "wrongPass",
                "Wrong",
                "User"));
    }

    @Test
    public void test_findAllForEmptyDB_returnEmptyList() {
        //given
        //when
        List<Order> orderList = orderDao.findAll();
        //then
        assertThat(orderList).isEmpty();
    }

    @Test
    public void test_findAllForOneSavedOrderInDB_returnListWithOneOrder() {
        //given
        Order orderToSave = createOrderEntity(1L, OrderStatus.PENDING);
        orderDao.save(orderToSave);

        //when
        List<Order> retrievedOrder = orderDao.findAll();
        //then
        assertThat(retrievedOrder).containsExactly(orderToSave);
    }

    @Test
    public void test_findAllByUserIdWhereOrderStatusIsPending_returnPendingOrder() {
        //given
        Order pendingOrderToSave = createOrderEntity(42L, OrderStatus.PENDING);
        Order sentOrderToSave = createOrderEntity(42L, OrderStatus.SENT);
        Order incorrectUserOrderToSave = createOrderEntity(1L, OrderStatus.SENT);

        orderDao.save(pendingOrderToSave);
        orderDao.save(sentOrderToSave);
        orderDao.save(incorrectUserOrderToSave);

        //when
        List<Order> retrievedList = orderDao.findAllByUserIdWhereOrderStatusIsPending(42L);
        //then
        assertThat(retrievedList).containsExactly(pendingOrderToSave);
        assertThat(retrievedList.size()).isEqualTo(1);

    }

    private Order createOrderEntity(long userId, OrderStatus pending) {
        return new Order(
                UUID.randomUUID().getMostSignificantBits()&Long.MAX_VALUE,
                userId,
                LocalDateTime.now(),
                pending,
                Set.of());
    }
}
