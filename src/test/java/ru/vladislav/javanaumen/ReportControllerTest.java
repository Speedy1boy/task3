package ru.vladislav.javanaumen;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReportControllerTest {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.authentication = RestAssured.preemptive().basic("admin", "bobs");
    }

    @Test
    void testCreateReport() {
        given()
                .when()
                .post("/reports")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    void testGetReport() {
        var id = given()
                .when()
                .post("/reports")
                .then()
                .statusCode(200)
                .extract()
                .as(Long.class);

        given()
                .pathParam("id", id)
                .when()
                .get("/reports/{id}")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    void testGetReportNotFound() {
        given()
                .pathParam("id", 999999)
                .when()
                .get("/reports/{id}")
                .then()
                .statusCode(anyOf(is(404), is(500)));
    }
}
