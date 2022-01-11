package com.javacourse.specialist.dao.mapper;

import com.javacourse.specialist.connection.ConnectionPool;
import com.javacourse.specialist.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.javacourse.specialist.dao.ColumnName.*;



public class UserCreator {
    private static  UserCreator instance;

    private UserCreator(){}

    public static UserCreator getInstance(){
        if(instance == null){
            instance = new UserCreator();
        }
        return instance;
    }

      public User create(ResultSet resultSet) throws SQLException {   //public static
      User user = new User();
      user.setId(resultSet.getInt(USER_ID));
      user.setPassword(resultSet.getString(PASSWORD));
      user.setName(resultSet.getString(NAME));
      user.setSurname(resultSet.getString(SURMAME));
      user.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
      user.setUserRole(User.UserRoles.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
      user.setRegistrationStatus(User.RegistrationStatus.valueOf((resultSet.getString((REGISTRATION_STATUS).toUpperCase()))));

      return user;
    }
}
