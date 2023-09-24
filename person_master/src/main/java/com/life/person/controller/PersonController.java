package com.life.person.controller;

import com.life.person.dto.PersonDTO;
import com.life.person.model.Person;
import com.life.person.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/person")
@Tag(name="PersonController", description="Управление информацией о пользователях")
public class PersonController {

    private final PersonService personService;
    private final String serviceURL = "/v1/person";

    @Operation(
            summary = "Создание пользователя",
            description = "Создает нового пользователя"
    )
    @PostMapping
    public PersonDTO createPerson(@RequestBody Person person) {
        log.info("POST запрос к сервису {}. Параметры запроса: {}.", serviceURL, person.toString());
        return personService.createPerson(person);
    }

    @Operation(
            summary = "Получение пользователя по id",
            description = "Возвращает информацию о пользователе по id записи"
    )
    @GetMapping("/{personId}")
    public PersonDTO getPersonById(@PathVariable @Parameter(description = "Идентификатор пользователя") Long personId) {
        log.info("GET запрос к сервису {}/{personId}. С id = {}.", serviceURL, personId);
        return personService.getPersonByIdDTO(personId);
    }

    @Operation(
            summary = "Получение пользователя по логину",
            description = "Возвращает информацию о пользователе по логину"
    )
    @GetMapping("/{login}/login")
    public PersonDTO getPersonByLogin(@PathVariable @Parameter(description = "Логин пользователя") String login) {
        log.info("GET запрос к сервису {}/{login}/login. С Логином = {}.", serviceURL, login);
        return personService.getPersonByLogin(login);
    }

    @Operation(
            summary = "Получение листа пользователей",
            description = "Возвращает всех пользователей с сортировкой по логину"
    )
    @GetMapping
    public List<PersonDTO> getPersonList() {
        log.info("GET запрос к сервису {}. Получение листа пользователей.", serviceURL);
        return personService.getPersonList();
    }

    @Operation(
            summary = "Изменение информации о пользователе",
            description = "Меняет логин и/или пароль"
    )
    @PatchMapping("/{personId}")
    public PersonDTO patchPerson(@PathVariable @Parameter(description = "Идентификатор пользователя") Long personId,
                                 @RequestBody Person person) {
        log.info("PATCH запрос к сервису {}/{personId}. С id = {}. Параметры запроса: {}.",
                serviceURL, personId, person.toString());
        return personService.patchPerson(personId, person);
    }

    @Operation(
            summary = "Удаление информации о пользователе",
            description = "Удаление информации по id пользователя"
    )
    @DeleteMapping("/{personId}")
    public void deletePersonById(@PathVariable @Parameter(description = "Идентификатор пользователя") Long personId) {
        log.info("DELETE запрос к сервису {}/{personId}. С id = {}.", serviceURL, personId);
        personService.deletePersonById(personId);
    }
}
