package com.isma.school_ms_schools.data.Converters;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;

import java.text.ParseException;
import java.util.List;

/***
 *
 * @param <U>
 * @param <V>
 */
public interface IConverter<U,V> {
    public V convertToDto(U u);
    public List<V> convertListToListDto(List<U> us);
    public U convertToEntity(V v) throws NoDataFoundException, ParseException;
    public List<U> convertListDtoToListEntity(List<V> vs) throws NoDataFoundException;
}