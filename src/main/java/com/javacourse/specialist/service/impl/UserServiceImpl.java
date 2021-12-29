package com.javacourse.specialist.service.impl;

import com.javacourse.specialist.dao.DaoProvider;
import com.javacourse.specialist.dao.UserDao;
import com.javacourse.specialist.dao.mapper.UserCreator;
import com.javacourse.specialist.entity.User;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.ServiceException;
import com.javacourse.specialist.service.UserService;
import com.javacourse.specialist.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = LogManager.getLogger();
    private UserDao userDao = DaoProvider.getInstance().getUserDao();
    @Override
    public void addUser(String name, String surname, int phone) throws ServiceException {

        User user = null;
        try {
           if(user != userDao.findUserByPhone(phone)){
               LOGGER.info("Attemption to create existent user");
           }else{
               user = new User();
               if(UserValidator.isValidName(name)&& UserValidator.isValidSurname(surname)&&UserValidator.isValidPhone(String.valueOf(phone))){
                   user.setPhoneNumber(phone);
                   user.setName(name);
                   user.setSurname(surname);
               }
               user.setUserRole(User.UserRoles.CLIENT);
               user.setPassword("????");
               user.setLogin("????");
               user.setRegistrationStatus(User.RegistrationStatus.REGISTERED);
           }
        } catch (DaoException e) {
            throw new ServiceException();
        }

//        String login = user.getLogin();
//        user.getName();
//        user.getSurname();
//        user.getPassword();
//        user.getPhoneNumber();
//        user.set
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        return null;
    }

    @Override
    public Optional<User> findUserById(int id) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public void removeUserById(int id) throws ServiceException {

    }
}
