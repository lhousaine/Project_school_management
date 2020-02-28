package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.ImplConverters.Mapper;
import com.isma.school_ms_schools.data.Dto.PersonDTO;
import com.isma.school_ms_schools.data.Entities.Person;
import com.isma.school_ms_schools.data.Repositories.PersonRepository;
import com.isma.school_ms_schools.service.iservices.IPersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.isma.school_ms_schools.core.params.GlobalParams.DATEFORMAT;

@Service
public class PersonService implements IPersonService {
    private final PersonRepository personRepository;
    private final Mapper mapper;

    public PersonService(PersonRepository personRepository, Mapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PersonDTO> getAll() throws NoDataFoundException {
        return mapper.toPersonDTOS(personRepository.findAll());
    }

    @Override
    public PersonDTO getById(Long id) throws NoDataFoundException {
            if (personRepository.findById(id).isPresent())
                return new PersonDTO(personRepository.findById(id).get());
            else throw new RuntimeException("Person not found");
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException {
        Person person=mapper.toPersonEntity(personDTO);
        if(!Pattern.matches(DATEFORMAT,personDTO.getBirthday()))
            throw new DateFormatException(person.getBirthday()+"date format not respected");
        return new PersonDTO(personRepository.save(person));
    }

    @Override
    public boolean update(Long idPerson, PersonDTO personDTO) throws NoDataFoundException, DataAlreadyUsed {
        Person person=mapper.toPersonEntity(personDTO);
        Person person1=personRepository.findById(idPerson).get();
        if(person1==null)
            throw new DataAlreadyUsed("No Person was identified by "+idPerson);
        person1.setAddress(person.getAddress());
        try {
            personRepository.save(person1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long idPerson) throws NoDataFoundException {
        if (!personRepository.findById(idPerson).isPresent()) {
            throw new NoDataFoundException("No Parent identified by " + idPerson);
        }
        try{
            personRepository.deleteById(idPerson);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
