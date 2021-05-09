package com.person.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PhoneDto {
    private String number;
    private String type;
}
