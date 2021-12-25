package com.javacourse.specialist.dao.impl;

import com.javacourse.specialist.connection.ConnectionPool;
import com.javacourse.specialist.dao.ProcedureDao;
import com.javacourse.specialist.dao.mapper.ProcedureCreator;
import com.javacourse.specialist.entity.Procedure;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javacourse.specialist.dao.ColumnName.*;

public class ProcedureDaoImpl implements ProcedureDao {
    private static final Logger LOGGER = LogManager.getLogger();
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADD_PROCEDURE = "INSERT INTO procedure (duration, price, procedure_type) VALUES(?, ?, ?)";
    private static final String FIND_PROCEDURE_BY_ID = "SELECT procedure_id, duration, price, procedure_type FROM procedure WHERE id=?";
    private static final String FIND_PROCEDURE_BY_USER_ID =
            "SELECT p.procedure_id, p.duration, p.price, p.procedure_type FROM procedure p " +
                    "JOIN orders o ON p.procedure_id= o.procedure_id WHERE o.user_id=?";
    private static final String REMOVE_PROCEDURE_BY_ID = "DELETE FROM procedure WHERE id=?";


   @Override
  public void addProcedure(Procedure procedure) throws DaoException{
     try(
             Connection dbConnection = connectionPool.getConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(ADD_PROCEDURE))
     {
         preparedStatement.setInt(1, procedure.getDuration());
         preparedStatement.setDouble(2, procedure.getPrice());
         preparedStatement.setString(3, procedure.getProcedureType());

         preparedStatement.executeUpdate();
     }catch(SQLException | DatabaseConnectionException e){
         LOGGER.error("Exception thrown 'addProcedure' method: " + e);
         throw new DaoException(e);

     }
   }

    @Override
    public Optional<Procedure> findProcedureById(int procedureId) throws DaoException{
        Optional<Procedure> procedure = Optional.empty();
        try(
                Connection dbConnection = connectionPool.getConnection();
                PreparedStatement preparedStatement = dbConnection.prepareStatement(FIND_PROCEDURE_BY_ID))
        {
         preparedStatement.setInt(1, procedureId);
         ResultSet resultSet = preparedStatement.executeQuery();
         while(resultSet.next()){
             Procedure procedureType = ProcedureCreator.create(resultSet);
             procedure = Optional.of(procedureType);
         }
         return procedure;
        }catch(SQLException | DatabaseConnectionException e){
            LOGGER.error("Exception thrown 'findProcedureById' method: " +e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Procedure> findProcedureByUserId(int userId) throws DaoException{
        List<Procedure> procedures = new ArrayList<>();
        try(
                Connection dbConnection = connectionPool.getConnection();
                PreparedStatement preparedStatement = dbConnection.prepareStatement(FIND_PROCEDURE_BY_USER_ID))
        {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Procedure procedure = new Procedure();
                procedure.setProcedureId(resultSet.getInt(PROCEDURE_ID));
                procedure.setDuration(resultSet.getInt(PROCEDURE_DURATION));
                procedure.setPrice(resultSet.getDouble(PROCEDURE_PRICE));
                procedure.setProcedureType(resultSet.getString(PROCEDURE_TYPE));

                procedures.add(procedure);
            }
            return procedures;
        }catch(SQLException | DatabaseConnectionException e){
            LOGGER.error("Exception thrown 'findProcedureByUserId' method: " +e);
            throw new DaoException(e);
        }
    }

    @Override
    public void removeProcedureById(int procedureId) throws DaoException{
      try ( Connection dbConnection = connectionPool.getConnection();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(REMOVE_PROCEDURE_BY_ID))
      {
          preparedStatement.setInt(1, procedureId);
          preparedStatement.executeUpdate();
      }catch(SQLException | DatabaseConnectionException e){
          LOGGER.error("Exception thrown 'removeProcedureById' method: " + e);
          throw new DaoException(e);
      }
    }
}
