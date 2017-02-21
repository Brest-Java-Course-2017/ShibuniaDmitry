package epam.com.app;

import java.util.List;

/**
 * Created by dimgo on 15.2.17.
 */
public interface UserDao {
    List<User> getAllUsers();

    User getUserById(Integer userId);

    User getUserByLogin(String login);

    Integer addUser(User user);

    void updateUser(User user);

    void deleteUser(Integer userId);
}
