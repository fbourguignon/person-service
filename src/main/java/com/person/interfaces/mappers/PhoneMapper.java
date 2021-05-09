package com.person.interfaces.mappers;

import com.person.application.payload.phone.PhoneRequest;
import com.person.domain.entities.Phone;
import com.person.domain.enumerator.PhoneType;
import com.person.interfaces.dtos.PhoneDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PhoneMapper {

    public static Phone toEntity(PhoneRequest phoneRequest){
        return Phone
                .builder()
                .type(PhoneType.valueOf(phoneRequest.getType()))
                .number(phoneRequest.getNumber())
                .build();
    }

    public static PhoneDto toDto(Phone phone){
        return PhoneDto
                .builder()
                .type(phone.getType().name())
                .number(phone.getNumber())
                .build();
    }
}
