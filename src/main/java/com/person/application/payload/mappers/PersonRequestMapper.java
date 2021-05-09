package com.person.application.payload.mappers;

import com.person.application.payload.person.PersonRequest;
import com.person.domain.entities.Person;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PersonRequestMapper {

    public static Person map(PersonRequest personRequest) {

        final List phones = personRequest
                .getPhones()
                .stream()
                .map(PhoneRequestMapper::map)
                .collect(Collectors.toList());

        var person = Person.builder()
                .birth(personRequest.getBirth())
                .name(personRequest.getName())
                .phones(phones)
                .build();

        return person;
    }

}
