package com.person.integration;

import com.person.application.payload.person.PersonRequest;
import com.person.application.payload.phone.PhoneRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import static io.restassured.http.Method.POST;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    private final String ENDPOINT = "/api/v1/persons";

    @Test
    public void mustCreatePersonWhenSendRequest() {

        LocalDate localDate = LocalDate.of(1995, 5, 27);
        Date birth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        var body = PersonRequest
                .builder()
                .name("Jhon")
                .birth(birth)
                .phones(Arrays.asList(PhoneRequest
                        .builder()
                        .number("2198998877")
                        .type("MOBILE")
                        .build()))
                .build();

        request()
                .body(body)
                .when()
                .request(POST, ENDPOINT)
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Jhon"))
                .body("birth", equalTo("1995-05-27"))
                .body("phones[0].number", equalTo("2198998877"))
                .body("phones[0].type", equalTo("MOBILE")
                );
    }


}
