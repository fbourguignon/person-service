package com.person.integration;

import com.person.application.payload.person.PersonRequest;
import com.person.application.payload.phone.PhoneRequest;
import com.person.interfaces.dtos.PersonDto;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Arrays;

import static io.restassured.http.Method.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    private final String ENDPOINT = "/api/v1/persons";
    private final String ENDPOINT_WITH_ID = ENDPOINT.concat("/%s");

    private PersonDto personDto;

    @Test
    @Order(1)
    public void mustCreatePersonWhenSendRequest() {

        LocalDate birth = LocalDate.of(1995, 5, 27);

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

        this.personDto = request()
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
                ).extract()
                .as(PersonDto.class);
    }

    @Test
    @Order(2)
    public void mustUpdatePersonWhenSendRequest() {

        LocalDate birth = LocalDate.of(1995, 5, 27);

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
                .request(PUT, String.format(ENDPOINT_WITH_ID, this.personDto.getId().toString()))
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo("Jhon"))
                .body("birth", equalTo("1995-05-27"))
                .body("phones[0].number", equalTo("2198998877"))
                .body("phones[0].type", equalTo("MOBILE")
                ).extract()
                .as(PersonDto.class);
    }

    @Test
    @Order(3)
    public void mustRetrievePersonWhenSendRequest() {

        request()
                .when()
                .request(GET, String.format(ENDPOINT_WITH_ID, this.personDto.getId().toString()))
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo("Jhon"))
                .body("birth", equalTo("1995-05-27"))
                .body("phones[0].number", equalTo("2198998877"))
                .body("phones[0].type", equalTo("MOBILE")
                ).extract()
                .as(PersonDto.class);
    }

    @Test
    @Order(4)
    public void mustListPersonWhenSendRequest() {

        request()
                .when()
                .request(GET, ENDPOINT)
                .then()
                .statusCode(200)
                .body("content[0].id", notNullValue())
                .body("content[0].name", equalTo("Jhon"))
                .body("content[0].birth", equalTo("1995-05-27"))
                .body("content[0].phones[0].number", equalTo("2198998877"))
                .body("content[0].phones[0].type", equalTo("MOBILE"));
    }

    @Test
    @Order(5)
    public void mustDeletePersonWhenSendRequest() {
        request()
                .when()
                .request(DELETE, String.format(ENDPOINT_WITH_ID, this.personDto.getId().toString()))
                .then()
                .statusCode(204);
    }

}
