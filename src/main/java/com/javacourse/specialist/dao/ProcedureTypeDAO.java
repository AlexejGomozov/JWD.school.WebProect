package com.javacourse.specialist.dao;

import com.javacourse.specialist.entity.ProcedureType;
import com.javacourse.specialist.exception.DaoException;

import java.util.List;


public interface ProcedureTypeDAO {
    void addProcedure(ProcedureType procedureType) throws DaoException;
    ProcedureType findProcedureById(int procedureTypeId) throws DaoException;
    List<ProcedureType> findProcedureByUserId(int userId) throws DaoException;
    void removeProcedureById(int procedureTypeId) throws DaoException;
}
