package api.ms.users.ms.users.services.servicesimpli;

import api.ms.users.ms.users.core.mapper.Mapper;
import api.ms.users.ms.users.dto.PersonDTO;
import api.ms.users.ms.users.models.Person;
import api.ms.users.ms.users.repositories.PersonRepository;
import api.ms.users.ms.users.services.iservices.IPersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements IPersonService {
    private PersonRepository personRepository;
    private Mapper mapper = new Mapper();

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public PersonDTO getPersonById(Long id) {
        if (personRepository.findById(id).isPresent())
            return new PersonDTO(personRepository.findById(id).get());
        else throw new RuntimeException("Person not found");
    }

    public List<PersonDTO> getPersons() {
        return mapper.toPersonDTOS(personRepository.findAll());
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

}
