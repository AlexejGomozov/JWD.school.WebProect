package com.javacourse.specialist.service;

import com.javacourse.specialist.entity.User;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean addUser(String name, String surname, String password, String phone) throws ServiceException;
    boolean authenticate(String login, String password) throws ServiceException;
    List<User> findAllUser() throws ServiceException;
    Optional<User> findUserById(int id) throws ServiceException;
    boolean removeUserById(int id) throws ServiceException;
}
