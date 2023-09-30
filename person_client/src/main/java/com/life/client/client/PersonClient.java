package com.life.client.client;

import com.life.model.person.Person;

public interface PersonClient {

    Person getPersonByIdSync(final Long id);
}
