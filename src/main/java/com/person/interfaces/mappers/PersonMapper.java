package com.person.interfaces.mappers;

import com.person.application.payload.person.PersonRequest;
import com.person.domain.entities.Person;
import com.person.domain.entities.Phone;
import com.person.interfaces.dtos.PersonDto;
import com.person.interfaces.dtos.PhoneDto;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class PersonMapper {

    public static Person toEntity(PersonRequest personRequest){
        List<Phone> phoneList = personRequest
                                .getPhones()
                                .stream()
                                .map(PhoneMapper::toEntity)
                                .collect(Collectors.toList());
        return Person
                .builder()
                .name(personRequest.getName())
                .birth(personRequest.getBirth())
                .phones(phoneList)
                .build();
    }

    public static PersonDto toDto(Person person){
        List<PhoneDto> phoneList = person
                .getPhones()
                .stream()
                .map(PhoneMapper::toDto)
                .collect(Collectors.toList());

        return PersonDto
                .builder()
                .id(person.getId())
                .name(person.getName())
                .birth(person.getBirth())
                .phones(phoneList)
                .build();
    }
}
