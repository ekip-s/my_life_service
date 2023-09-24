package com.life.person.dto;

import com.life.person.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;
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
