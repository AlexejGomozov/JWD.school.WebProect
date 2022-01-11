package com.javacourse.specialist.dao;

import com.javacourse.specialist.entity.User;
import com.javacourse.specialist.exception.DaoException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> signIn(String phoneNumber, String password) throws DaoException;
    void addUser(User user) throws DaoException;
    List<User> findAllUser() throws DaoException;
    Optional<User> findUserByPhone(String phone) throws DaoException;
    Optional <User> findUserById(int id) throws DaoException;
    boolean removeUserById(int id) throws DaoException;
}
