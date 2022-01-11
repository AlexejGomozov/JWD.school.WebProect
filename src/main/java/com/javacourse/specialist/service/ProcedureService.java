package com.javacourse.specialist.service;

import com.javacourse.specialist.entity.Procedure;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProcedureService {
    boolean addProcedure(int duration, BigDecimal price, String procedureType);
    Optional<Procedure> findProcedureById(int procedureId) throws ServiceException;
    List<Procedure> findProcedureByUserId(int userId) throws ServiceException;
    List<Procedure> findAllProcedure(int userId) throws ServiceException;
    Set<Procedure> findAllProcedureByType(String procedureType) throws ServiceException;
    boolean removeProcedureById(int procedureId) throws ServiceException;
}
