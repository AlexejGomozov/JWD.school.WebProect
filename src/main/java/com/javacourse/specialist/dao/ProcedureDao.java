package com.javacourse.specialist.dao;

import com.javacourse.specialist.entity.Procedure;
import com.javacourse.specialist.exception.DaoException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProcedureDao {
    void addProcedure(Procedure procedure) throws DaoException;
    Optional<Procedure> findProcedureById(int procedureId) throws DaoException;
    Set<Procedure> findAllProcedureByType(String procedureType) throws DaoException;
    List<Procedure> findProcedureByUserId(int userId) throws DaoException;
    void removeProcedureById(int procedureId) throws DaoException;
}
