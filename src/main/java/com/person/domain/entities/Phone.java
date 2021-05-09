package com.person.domain.entities;

import com.person.domain.enumerator.PhoneType;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Phone {
    private String number;
    private PhoneType type;
}
