package com.javacourse.specialist.dao.mapper;

import com.javacourse.specialist.entity.Procedure;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.javacourse.specialist.dao.ColumnName.*;

public class ProcedureCreator {
    private static ProcedureCreator instance;
    private ProcedureCreator(){}

    public static ProcedureCreator getInstance(){
        if(instance == null){
            instance = new ProcedureCreator();
        }
        return instance;
    }

    public Procedure create(ResultSet resultSet) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setProcedureId(resultSet.getInt(PROCEDURE_ID));
        procedure.setDuration(resultSet.getInt(PROCEDURE_DURATION));
        procedure.setPrice(resultSet.getBigDecimal(PROCEDURE_PRICE));
        procedure.setProcedureType(resultSet.getString(PROCEDURE_TYPE));

        return procedure;
    }
}
