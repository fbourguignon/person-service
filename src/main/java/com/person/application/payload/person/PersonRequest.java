package com.person.application.payload.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.person.application.payload.phone.PhoneRequest;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class PersonRequest {
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR")
    private LocalDate birth;
    @Builder.Default
    private List<PhoneRequest> phones = new ArrayList<>();
}
