package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;

import java.text.ParseException;
import java.util.List;

public interface IAbstractService<ID,U> {
    public List<U> getAll() throws NoDataFoundException;
    public U getById(ID id) throws NoDataFoundException;
    public U create(U u) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException;
    public boolean update(ID id,U u) throws NoDataFoundException, DataAlreadyUsed, ParseException;
    public boolean delete(ID id) throws NoDataFoundException;
}
