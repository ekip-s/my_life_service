package com.life.person.service;

import com.life.model.person.Person;
import com.life.model.person.PersonDTO;
import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonDTO createPerson(Person person);
    PersonDTO getPersonByIdDTO(UUID id);
    PersonDTO getPersonByLogin(String login);
    List<PersonDTO> getPersonList();
    PersonDTO patchPerson(UUID id, Person person);

    void deletePersonById(UUID id);
}
