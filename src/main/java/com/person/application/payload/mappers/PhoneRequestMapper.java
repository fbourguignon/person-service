package com.person.application.payload.mappers;

import com.person.application.payload.phone.PhoneRequest;
import com.person.domain.entities.Phone;
import com.person.domain.enumerator.PhoneType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PhoneRequestMapper {
    public static Phone map(PhoneRequest phoneRequest) {
        return Phone.builder()
                .number(phoneRequest.getNumber())
                .type(PhoneType.valueOf(phoneRequest.getType()))
                .build();
    }
}
