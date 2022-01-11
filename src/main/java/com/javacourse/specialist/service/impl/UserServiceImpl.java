package com.javacourse.specialist.service.impl;

import com.javacourse.specialist.dao.DaoProvider;
import com.javacourse.specialist.dao.UserDao;
import com.javacourse.specialist.entity.User;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.ServiceException;
import com.javacourse.specialist.service.UserService;
import com.javacourse.specialist.util.PasswordEncryptor;
import com.javacourse.specialist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserDao userDao = DaoProvider.getInstance().getUserDao();


    @Override
    public boolean addUser(String name, String surname, String password, String phone) throws ServiceException {
        boolean isAddUser = true;
        final Validator validator = Validator.getInstance();
        User user;
        try {
           if(userDao.findUserByPhone(phone).isPresent()){
               LOGGER.info("Attemption to create existent user");
               return !isAddUser;
           }else{
               user = new User();
               if(validator.isValidPassword(password) &&
                       validator.isValidName(name)&&
                       validator.isValidSurname(surname)&&
                       validator.isValidPhone(phone))
               {
                   String encryptedPassword = PasswordEncryptor.getInstance().getHash(password);
                   user.setPhoneNumber(phone);
                   user.setName(name);
                   user.setSurname(surname);
                   user.setPassword(encryptedPassword);
               }
               user.setUserRole(User.UserRoles.CLIENT);
               user.setRegistrationStatus(User.RegistrationStatus.REGISTERED);
           }

        } catch (DaoException e) {
            LOGGER.error("Exception while method 'addUser': " + e.getMessage());
            throw new ServiceException(e);
        }
        return isAddUser;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        String userPass = "";
        final Validator validator = Validator.getInstance();
        try {
            if(validator.isValidPhone(login) && validator.isValidPassword(password)){
               Optional <User> userOptional = userDao.findUserByPhone(login);
               if(userOptional.isPresent()){
                userPass = userOptional.get().getPassword();}
            }
        }catch(DaoException e){
            LOGGER.error("Exception while method 'authenticate': " + e.getMessage());
            throw new ServiceException(e);
        }
        String encryptedPassword = PasswordEncryptor.getInstance().getHash(password);
        return PasswordEncryptor.getInstance().checkHash(encryptedPassword, userPass);
    }

    @Override
    public List<User> findAllUser() throws ServiceException {
        try {
            return userDao.findAllUser();
        } catch (DaoException e) {
            LOGGER.error("Exception while method 'findAllUsers': " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findUserById(int id) throws ServiceException {
        final Validator validator = Validator.getInstance();
        if (validator.isValidId(String.valueOf(id))) {
            try {
                return userDao.findUserById(id);
            } catch (DaoException e) {
                LOGGER.error("Exception while method 'findUserById': " + e.getMessage());
                throw new ServiceException(e);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean singOut(String login) throws ServiceException {
        return false;
    }

    @Override
    public boolean removeUserById(int id) throws ServiceException {
        boolean isRemove = true;
        final Validator validator = Validator.getInstance();
        if (validator.isValidId(String.valueOf(id))) {
            try {
                userDao.removeUserById(id);
                return isRemove;
            } catch (DaoException e) {
                LOGGER.error("Exception while method 'removeUserById': " + e.getMessage());
                throw new ServiceException(e);
            }
        }
        return !isRemove;
    }
}
