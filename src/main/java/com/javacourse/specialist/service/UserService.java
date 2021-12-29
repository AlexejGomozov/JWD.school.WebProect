package com.javacourse.specialist.service;

import com.javacourse.specialist.entity.User;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(String name, String surname, int phone) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    Optional<User> findUserById(int id) throws ServiceException;
    void removeUserById(int id) throws ServiceException;
}
