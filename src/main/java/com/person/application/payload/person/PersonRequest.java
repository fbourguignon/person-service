package com.person.application.payload.person;

import com.person.application.payload.phone.PhoneRequest;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class PersonRequest {
    private String name;
    private Date birth;
    @Builder.Default
    private List<PhoneRequest> phones = new ArrayList<>();
}