package com.ccat.ordersys.repository;

import com.ccat.ordersys.model.entity.User;
import com.ccat.ordersys.model.repository.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties")
public class UserRepositoryTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test_findAllForEmptyDB_returnEmptyList() {
        //given
        //when
        List<User> retrievedList = userDao.findAll();
        //then
        assertThat(retrievedList).isEmpty();
    }

    @Test
    public void test_findAllForOneSavedUserInDB_returnListWithOneUser() {
        //given
        User userToSave = new User(
                1L,
                "testusr@test.com",
                "testpass1",
                "Test",
                "User"
        );
        userDao.save(userToSave);

        //when
        List<User> retrievedList = userDao.findAll();
        //then
        assertThat(retrievedList).containsExactly(userToSave);
    }
}
