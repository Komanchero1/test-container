package org.example.conditional_application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> devContainer = new GenericContainer<>("devapp:latest").withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> prodContainer = new GenericContainer<>("prodapp:latest").withExposedPorts(8081);

    @BeforeAll
    static void setup() {
        devContainer.start();
        prodContainer.start();
    }

    @Test
    void testDevProfile() {
        int mappedPort = devContainer.getMappedPort(8080);
        String url = "http://localhost:" + mappedPort + "/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Current profile is dev", response.getBody());
    }

    @Test
    void testProdProfile() {
        int mappedPort = prodContainer.getMappedPort(8081);
        String url = "http://localhost:" + mappedPort + "/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Current profile is production", response.getBody());

    }
}

