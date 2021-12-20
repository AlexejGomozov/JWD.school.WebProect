package com.javacourse.specialist.dao;

import com.javacourse.specialist.entity.Users;
import com.javacourse.specialist.exception.DaoException;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void addUser(Users user) throws DaoException;
    List<Users> findAllUser() throws DaoException;
    Optional<Users> findUserById(int id) throws DaoException;
    void removeUserById(int id) throws DaoException;
}
