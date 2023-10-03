package com.life.person.service;

import com.life.exception.ConflictException;
import com.life.exception.NotFoundException;
import com.life.model.person.Person;
import com.life.model.person.PersonDTO;
import com.life.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public PersonDTO createPerson(Person person) {
        checkPersonLogin(person);
        person.setCreateDT(LocalDateTime.now());
        return new PersonDTO().toDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO getPersonByIdDTO(UUID id) {
        return new PersonDTO().toDTO(personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Некорректный запрос",
                        "Нет пользователя с id: " + id + ".")));
    }

    @Override
    public PersonDTO getPersonByLogin(String login) {
        return new PersonDTO().toDTO(personRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Некорректный запрос",
                        "Нет пользователя с логином: " + login + ".")));
    }

    @Override
    public List<PersonDTO> getPersonList() {
        return new PersonDTO().toDTO(personRepository
                .findAll(Sort.by("login")));
    }

    @Override
    @Transactional
    public PersonDTO patchPerson(UUID id, Person person) {
        Person personDB = getPersonById(id);
        if(person.getLogin() != null && !person.getLogin().isBlank()) {
            checkPersonLogin(person);
            personDB.setLogin(person.getLogin());
        }
        if(person.getPassword() != null && !person.getPassword().isBlank()) {
            personDB.setPassword(person.getPassword());
        }
        return new PersonDTO().toDTO(personRepository.save(personDB));
    }

    @Override
    @Transactional
    public void deletePersonById(UUID id) {
        getPersonById(id);
        personRepository.deleteById(id);
    }

    private void checkPersonLogin(Person person) {
        if(personRepository.findByLogin(person.getLogin()).isPresent()) {
            throw new ConflictException("Логин занят", "Логин " + person.getLogin() + "занят, выберите другой");
        }
    }

    private Person getPersonById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Некорректный запрос",
                        "Нет пользователя с id: " + id + "."));
    }
}
