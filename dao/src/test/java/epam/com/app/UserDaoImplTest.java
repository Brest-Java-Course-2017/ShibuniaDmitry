package epam.com.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Test
    public void getAllUsersTest() throws Exception {

        List<User> users = userDao.getAllUsers();
        assertTrue(users.size() == 2);
    }

    @Test
    public void getUserByIdTest() throws Exception {

        User user = userDao.getUserById(1);
        assertNotNull(user);
        assertEquals("userLogin1", user.getLogin());
    }

    @Test
    public void getUserByLoginTest() throws Exception {

        User user = userDao.getUserByLogin("userLogin1");
        assertNotNull(user);
        assertEquals("userLogin1", user.getLogin());
    }

    @Test
    public void addUserTest() throws Exception{
        User user = new User();
        user.setLogin("User3");
        user.setPassword("password");
        user.setDescription("description of user 3");
        user.setUserId(userDao.addUser(user));
        assertNotNull(user.getUserId());
        assertTrue(user.getUserId()==3);
    }
    @Test
    public void updateUserTest() throws Exception{
        User user = new User(2, "newLogin", "newPassword", "newDescription");
        userDao.updateUser(user);
        User updatedUser = userDao.getUserById(user.getUserId());
        assertNotNull(updatedUser);
        assertEquals(user.getLogin(), updatedUser.getLogin());
    }

    @Test
    public void deleteUserTest() throws Exception{
        int size = userDao.getAllUsers().size();
        userDao.deleteUser(2);
        assertTrue(userDao.getAllUsers().size() == size -1);
    }
}