package com.life.client.client;

import com.life.model.person.Person;

import java.util.UUID;

public interface PersonClient {

    Person getPersonByIdSync(final UUID id);
}
