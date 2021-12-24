package com.javacourse.specialist.dao.mapper;

import com.javacourse.specialist.entity.Procedure;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.javacourse.specialist.dao.ColumnName.*;

public class ProcedureCreator {

    private ProcedureCreator(){}

    public static Procedure create(ResultSet resultSet) throws SQLException {
        Procedure procedure = new Procedure();

        //procedureType.setProcedureTypeId(resultSet.getInt(PROCEDURE_TYPE_ID));
        procedure.setDuration(resultSet.getInt(PROCEDURE_DURATION));
        procedure.setPrice(resultSet.getDouble(PROCEDURE_PRICE));
        procedure.setProcedureType(resultSet.getString(PROCEDURE_TYPE));

        return procedure;
    }
}
