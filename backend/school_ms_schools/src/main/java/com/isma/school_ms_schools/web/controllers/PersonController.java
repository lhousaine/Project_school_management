package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.PersonDTO;
import com.isma.school_ms_schools.data.Entities.Person;
import com.isma.school_ms_schools.service.implServices.PersonService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/persons")
@Api(value="clients API", description="clients")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getPersons() throws NoDataFoundException {
        return this.personService.getAll();
    }

    @GetMapping(path = "/{id}")
    public PersonDTO getPersonById(@PathVariable Long id) throws NoDataFoundException {
        return this.personService.getById(id);
    }

    @PostMapping(path = "/register/person")
    public PersonDTO savePerson(@RequestBody PersonDTO personDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException {
       return this.personService.create(personDTO);
    }

    @PutMapping(path = "/{id}")
    public  boolean updatePerson(@PathVariable Long id,@RequestBody PersonDTO personDTO) throws NoDataFoundException, DataAlreadyUsed {
        return personService.update(id,personDTO);
    }

    @DeleteMapping(path = "/{id}")
    public  boolean deletePerson(@PathVariable Long id) throws NoDataFoundException{
        return personService.delete(id);
    }
}
