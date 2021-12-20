package com.javacourse.specialist.dao.impl;

import com.javacourse.specialist.entity.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.javacourse.specialist.dao.ColumnName.*;

public class UserCreator {

    private UserCreator(){}

    static Users create(ResultSet resultSet) throws SQLException {
      Users user = new Users();

      user.setId(resultSet.getInt(USERS_ID));
      user.setLogin(resultSet.getString(LOGIN));
      user.setPassword(resultSet.getString(PASSWORD));
      user.setName(resultSet.getString(NAME));
      user.setSurname(resultSet.getString(SURMAME));
      user.setPhoneNumber(resultSet.getInt(PHONE_NUMBER));
      user.setUserRole(Users.UserRoles.valueOf(resultSet.getString(USERS_ROLES).toUpperCase()));
      user.setRegistrationStatus(Users.RegistrationStatus.valueOf((resultSet.getString((REGISTRATION_STATUS).toUpperCase()))));

      return user;
    }
}
