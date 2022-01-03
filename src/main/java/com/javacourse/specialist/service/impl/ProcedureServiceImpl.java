package com.javacourse.specialist.service.impl;

import com.javacourse.specialist.dao.DaoProvider;
import com.javacourse.specialist.dao.ProcedureDao;
import com.javacourse.specialist.dao.UserDao;
import com.javacourse.specialist.entity.Procedure;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.ServiceException;
import com.javacourse.specialist.service.ProcedureService;
import com.javacourse.specialist.validator.ProcedureValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;

public class ProcedureServiceImpl implements ProcedureService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ProcedureDao procedureDao = DaoProvider.getInstance().getProcedureDao();

    @Override
    public boolean addProcedure(int duration, BigDecimal price, String procedureType)  {
        boolean isAddProcedure = true;
        Procedure procedure;
            procedure = new Procedure();
            if (ProcedureValidator.isValidName(procedureType) &&
                    ProcedureValidator.isValidPrice(String.valueOf(price)) &&
                    ProcedureValidator.isValidDuration(String.valueOf(duration))) {
                procedure.setProcedureType(procedureType);
                procedure.setPrice(price);
                procedure.setDuration(duration);
            }else{
                LOGGER.error("The entered data for method 'addUser' is not correct! ");
                return !isAddProcedure;
            }
        return isAddProcedure;
    }

    @Override
    public Optional<Procedure> findProcedureById(int procedureId) throws ServiceException {
        Optional<Procedure> procedureOptional;
        if(ProcedureValidator.isValidID(String.valueOf(procedureId))) {
            try {
                procedureOptional = procedureDao.findProcedureById(procedureId);
                if (procedureOptional.isPresent()) {
                    return procedureOptional;
                }

            } catch (DaoException e) {
                LOGGER.error("Exception while method 'findProcedureById': " + e.getMessage());
                throw new ServiceException(e);
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Procedure> findProcedureByUserId(int userId) throws ServiceException {
        if (ProcedureValidator.isValidID(String.valueOf(userId))) {
            try {
              return procedureDao.findProcedureByUserId(userId);
            } catch (DaoException e) {
                LOGGER.error("Exception while method 'findProcedureByUserId': " + e.getMessage());
                throw new ServiceException(e);
            }
        }
            return Collections.emptyList();
    }

    @Override
    public Set<Procedure> findAllProcedureByType(String procedureType) throws ServiceException {
        if (ProcedureValidator.isValidName(procedureType)) {
            try {
                return procedureDao.findAllProcedureByType(procedureType);
            } catch (DaoException e) {
                LOGGER.error("Exception while method 'findAllProcedureByType': " + e.getMessage());
                throw new ServiceException(e);
            }
        }
        return Collections.emptySet();
    }

    @Override
    public boolean removeProcedureById(int procedureId) throws ServiceException {
        boolean isRemoveProcedureById = true;
        if (ProcedureValidator.isValidID(String.valueOf(procedureId))) {
            try {
                procedureDao.removeProcedureById(procedureId);
                return isRemoveProcedureById;
            } catch (DaoException e) {
                LOGGER.error("Exception while method 'removeProcedureById': " + e.getMessage());
                throw new ServiceException(e);
            }
        }
        return !isRemoveProcedureById;
    }
}
