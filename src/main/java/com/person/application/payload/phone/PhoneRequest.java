package com.person.application.payload.phone;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Pattern;

@Builder
@Value
public class PhoneRequest {

    @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})\n")
    private String number;

    @Pattern(regexp = "^(MOBILE|BUSINESS)$",message = "Invalid type")
    private String type;
}
