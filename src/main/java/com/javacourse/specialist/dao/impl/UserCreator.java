package com.javacourse.specialist.dao.impl;

import com.javacourse.specialist.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.javacourse.specialist.dao.ColumnName.*;

public class UserCreator {

    private UserCreator(){}

    static User create(ResultSet resultSet) throws SQLException {
      User user = new User();

      user.setId(resultSet.getInt(USER_ID));
      user.setLogin(resultSet.getString(LOGIN));
      user.setPassword(resultSet.getString(PASSWORD));
      user.setName(resultSet.getString(NAME));
      user.setSurname(resultSet.getString(SURMAME));
      user.setPhoneNumber(resultSet.getInt(PHONE_NUMBER));
      user.setUserRole(User.UserRoles.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
      user.setRegistrationStatus(User.RegistrationStatus.valueOf((resultSet.getString((REGISTRATION_STATUS).toUpperCase()))));

      return user;
    }
}
