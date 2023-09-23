package com.life.person.service;

import com.life.person.dto.PersonDTO;
import com.life.person.model.Person;

import java.util.List;

public interface PersonService {

    PersonDTO createPerson(Person person);
    PersonDTO getPersonById(Long id);
    List<PersonDTO> getPersonList();
    PersonDTO patchPerson(Long id, Person person);

    void deletePersonById(Long id);
}
