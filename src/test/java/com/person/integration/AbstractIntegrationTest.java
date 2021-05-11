package com.person.integration;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = AbstractIntegrationTest.DockerPostgreDataSourceInitializer.class)
@Testcontainers
public class AbstractIntegrationTest {

    @LocalServerPort
    private Integer port;

    public static PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer<>("postgres:9.6");

    @BeforeAll
    public static void before(){
        postgreDBContainer.start();
    }

    protected RequestSpecification request() {
        return with()
                .port(port)
                .contentType(JSON);
    }

    public static class DockerPostgreDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + postgreDBContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreDBContainer.getUsername(),
                    "spring.datasource.password=" + postgreDBContainer.getPassword()
            );
        }
    }
}
