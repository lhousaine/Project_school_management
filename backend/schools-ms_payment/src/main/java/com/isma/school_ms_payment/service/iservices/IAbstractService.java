package com.isma.school_ms_payment.service.iservices;


import com.isma.school_ms_payment.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_payment.core.exceptions.NoDataFoundException;

import java.util.List;

public interface IAbstractService<ID,U> {
    public List<U> getAll() throws NoDataFoundException;
    public U getById(ID id) throws NoDataFoundException;
    public U create(U u) throws DataAlreadyUsed, NoDataFoundException;
    public boolean update(ID id,U u) throws NoDataFoundException,DataAlreadyUsed;
    public boolean delete(ID id) throws NoDataFoundException;
}

