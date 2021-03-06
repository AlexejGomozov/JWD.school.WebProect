package com.javacourse.specialist.dao.impl;

import com.javacourse.specialist.connection.ConnectionPool;
import com.javacourse.specialist.dao.ProcedureDao;
import com.javacourse.specialist.dao.mapper.ProcedureCreator;
import com.javacourse.specialist.dao.mapper.UserCreator;
import com.javacourse.specialist.entity.Procedure;
import com.javacourse.specialist.entity.User;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

import static com.javacourse.specialist.dao.ColumnName.*;

public class ProcedureDaoImpl implements ProcedureDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private static ProcedureDaoImpl instance;
   private final ConnectionPool connectionPool = ConnectionPool.getInstance();

   private ProcedureDaoImpl(){}

    public static ProcedureDaoImpl getInstance(){
       if(instance == null){
           instance = new ProcedureDaoImpl();
       }
       return instance;
    }

    private static final String ADD_PROCEDURE = "INSERT INTO procedure (duration, price, procedure_type) VALUES(?, ?, ?)";
    private static final String FIND_PROCEDURE_BY_ID = "SELECT procedure_id, duration, price, procedure_type FROM procedure WHERE id=?";
    private static final String FIND_PROCEDURE_BY_TYPE = "SELECT procedure_id, duration, price, procedure_type FROM procedure WHERE procedure_type=?";
    private static final String FIND_ALL_PROCEDURE = "SELECT procedure_id, duration, price, procedure_type FROM procedure";
    private static final String FIND_PROCEDURE_BY_USER_ID =
            "SELECT p.procedure_id, p.duration, p.price, p.procedure_type FROM procedure p " +
                    "JOIN orders o ON p.procedure_id=o.procedure_id WHERE o.user_id=?";
    private static final String REMOVE_PROCEDURE_BY_ID = "DELETE FROM procedure WHERE id=?";


   @Override
  public void addProcedure(Procedure procedure) throws DaoException{
     try(
             Connection dbConnection = connectionPool.getConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(ADD_PROCEDURE))
     {
         preparedStatement.setInt(1, procedure.getDuration());
         preparedStatement.setBigDecimal(2, procedure.getPrice());
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
         try(ResultSet resultSet = preparedStatement.executeQuery()) {
             while (resultSet.next()) {
                 Procedure procedureType = ProcedureCreator.getInstance().create(resultSet);   ///getInstance() ????
                 procedure = Optional.of(procedureType);
             }
             return procedure;
         }
        }catch(SQLException | DatabaseConnectionException e){
            LOGGER.error("Exception thrown 'findProcedureById' method: " +e);
            throw new DaoException(e);
        }
    }

    @Override
    public Set<Procedure> findAllProcedureByType(String procedureType) throws DaoException{
       Set<Procedure> procedures = new HashSet<>();
       try(
               Connection dBconnection = connectionPool.getConnection();
               PreparedStatement preparedStatement = dBconnection.prepareStatement(FIND_PROCEDURE_BY_TYPE))
       {
           preparedStatement.setString(1, procedureType);
         try(ResultSet resultSet = preparedStatement.executeQuery()) {
             while (resultSet.next()) {
                 Procedure procedure = ProcedureCreator.getInstance().create(resultSet);   ///getInstance() ???
                 procedures.add(procedure);
             }
             return procedures;
         }
       }catch(SQLException | DatabaseConnectionException e) {
           LOGGER.error("Exception thrown 'findAllProcedureByType' method: " +e);
           throw new DaoException(e);
       }
    }

    @Override
    public List<Procedure> findAllProcedure() throws DaoException {
        List<Procedure> procedures = new ArrayList<>();
        try (Connection dbConnection = connectionPool.getConnection();
             Statement statement = dbConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_PROCEDURE))
        {
            while(resultSet.next()){
                Procedure procedure = ProcedureCreator.getInstance().create(resultSet);  ////getInstance() ??????
                procedures.add(procedure);
            }
            return procedures;
        } catch (SQLException | DatabaseConnectionException e) {
            LOGGER.error("Exception while method 'findAllUser': " + e.getMessage());
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
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Procedure procedure = ProcedureCreator.getInstance().create(resultSet);   // getInstance()???
                procedures.add(procedure);
            }
            return procedures;
        }
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
