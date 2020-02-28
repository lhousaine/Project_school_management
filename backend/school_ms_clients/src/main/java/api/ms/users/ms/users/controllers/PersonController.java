package api.ms.users.ms.users.controllers;

import api.ms.users.ms.users.dto.PersonDTO;
import api.ms.users.ms.users.models.Person;
import api.ms.users.ms.users.services.servicesimpli.PersonService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/persons")
@Api(value="clients API", description="clients")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getPersons() {
        return this.personService.getPersons();
    }

    @GetMapping(path = "/{id}")
    public PersonDTO getPersonById(@PathVariable Long id) {
        return this.personService.getPersonById(id);
    }

    @PostMapping(path = "/register/person")
    public Person savePerson(@RequestBody Person person){
       return this.personService.savePerson(person);
    }

    @PutMapping(path = "/{id}")
    public  Person updatePerson(@RequestBody Person person,@PathVariable Long id){
        return personService.updatePerson(person);
    }
}
