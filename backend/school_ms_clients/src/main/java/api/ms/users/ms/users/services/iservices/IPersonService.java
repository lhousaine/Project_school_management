package api.ms.users.ms.users.services.iservices;

import api.ms.users.ms.users.dto.PersonDTO;
import api.ms.users.ms.users.models.Person;

import java.util.List;

public interface IPersonService {
    public Person savePerson(api.ms.users.ms.users.models.Person person);
    public PersonDTO getPersonById(Long id);
    public List<PersonDTO> getPersons();
    public Person updatePerson(Person person);
}
