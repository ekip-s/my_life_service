package com.life.person.service;

import com.life.person.dto.PersonDTO;
import com.life.person.model.Person;
import com.life.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDTO createPerson(Person person) {
        return null;
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return null;
    }

    @Override
    public List<PersonDTO> getPersonList() {
        return null;
    }

    @Override
    public PersonDTO patchPerson(Long id, Person person) {
        return null;
    }

    @Override
    public void deletePersonById(Long id) {

    }
}
