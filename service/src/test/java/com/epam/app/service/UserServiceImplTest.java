package com.epam.app.service;

import epam.com.app.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dimgo on 20.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml"})
@Transactional
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    private static final String USER_LOGIN_1 = "userLogin1";
    private static final Integer USER_ID = 1;
    private static final String NEW_USER_PASSWORD = "newpPssword";

    @Test
    public void getAllUsers() throws Exception {
        List<User> users = userService.getAllUsers();
        Assert.assertEquals("", 2, users.size());
    }

    @Test
    public void getUserById() throws Exception {
        User user = userService.getUserById(USER_ID);
        Assert.assertNotNull(user);
        Assert.assertEquals(USER_ID, user.getUserId());
    }

    @Test
    public void getUserByLogin() throws Exception {
        User user = userService.getUserByLogin(USER_LOGIN_1);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getLogin());
        Assert.assertEquals(USER_LOGIN_1, user.getLogin());
    }

    @Test
    public void addUser() throws Exception {
        User user = new User(null, "userLogin3", "userPassword3", "userDescription3");
        int userId = userService.addUser(user);
        Assert.assertTrue(userId == 3);
    }

    @Test
    public void updateUser() throws Exception {
        User user = userService.getUserById(USER_ID);
        user.setPassword(NEW_USER_PASSWORD);
        userService.updateUser(user);
        user = userService.getUserById(USER_ID);
        Assert.assertEquals(NEW_USER_PASSWORD, user.getPassword());
    }

    @Test
    public void deleteUser() throws Exception {
        int usersQuantity = userService.getAllUsers().size();
        userService.deleteUser(USER_ID);
        Assert.assertEquals(usersQuantity - 1, userService.getAllUsers().size());
    }

}