package com.person.application.payload.phone;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Pattern;

@Builder
@Value
public class PhoneRequest {
    private String number;

    @Pattern(regexp = "^(MOBILE|BUSINESS)$",message = "Invalid type")
    private String type;
}
