package com.life.client.client;

import com.life.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PersonClientImpl implements PersonClient {

    private final WebClient webClient;

    @Autowired
    public PersonClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public Person getPersonByIdSync(Long id) {
        return webClient
                .get()
                .uri(String.join("", "/api/v1/person/" + id))
                .retrieve()
                .bodyToMono(Person.class)
                .block();
    }
}
