package com.javacourse.specialist.dao;

import com.javacourse.specialist.entity.Users;
import com.javacourse.specialist.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUser(Users user) throws DaoException;
    List<Users> findAllUser() throws DaoException;
    Users findUserById(int id) throws DaoException;   // Optional ???    SQLException или свое исключение?
    void removeUserById(int id) throws DaoException;
}
