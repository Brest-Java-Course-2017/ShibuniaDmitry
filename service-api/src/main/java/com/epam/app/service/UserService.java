package com.epam.app.service;

import epam.com.app.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by dimgo on 20.2.17.
 */
public interface UserService {

    /**
     * Get all users list.
     *
     * @return all users list
     */
    List<User> getAllUsers() throws DataAccessException;

    /**
     * Get user by Id.
     *
     * @param userId user identifier.
     * @return user.
     */
    User getUserById(Integer userId) throws DataAccessException;

    /**
     * Get user by login.
     *
     * @param login user login.
     * @return user
     * @throws DataAccessException
     */
    User getUserByLogin(String login) throws DataAccessException;

    /**
     * Create new user.
     *
     * @param user user.
     * @return new user Id.
     */
    Integer addUser(User user) throws DataAccessException;

    /**
     * Update user.
     *
     * @param user user.
     * @return new user Id.
     */
    void updateUser(User user) throws DataAccessException;

    void deleteUser(Integer userId) throws DataAccessException;
}
