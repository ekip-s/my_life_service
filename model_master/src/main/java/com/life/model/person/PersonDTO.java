package com.life.model.person;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private UUID id;
    private String login;
    private LocalDateTime createDT;

    public PersonDTO toDTO(Person person) {
        return new PersonDTO(person.getId(), person.getLogin(), person.getCreateDT());
    }

    public List<PersonDTO> toDTO(List<Person> personList) {
        return personList
                .stream()
                .map(p -> toDTO(p))
                .collect(Collectors.toList());
    }
}
