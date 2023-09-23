package com.life.person.controller;

import com.life.person.dto.PersonDTO;
import com.life.person.model.Person;
import com.life.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonDTO createPerson(Person person) {
        return null;
    }

    public PersonDTO getPersonById(Long id) {
        return null;
    }

    public List<PersonDTO> getPersonList() {
        return null;
    }

    public PersonDTO patchPerson(Long id, Person person) {
        return null;
    }

    public void deletePersonById(Long id) {

    }
}
