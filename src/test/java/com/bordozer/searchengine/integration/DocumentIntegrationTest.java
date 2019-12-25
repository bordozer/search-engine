package com.bordozer.searchengine.integration;

import com.bordozer.searchengine.commons.AbstractIntegrationTest;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.ValidatableResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;

import static com.bordozer.commons.utils.FileUtils.readSystemResource;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@DatabaseSetup("/tests/DocumentIntegrationTest/initDB.xml")
@DatabaseTearDown("/tests/emptyDB.xml")
public class DocumentIntegrationTest extends AbstractIntegrationTest {

    @SneakyThrows
    @Test
    void shouldGetDocument() {

        RestAssured.baseURI = getBaseApi();
        RestAssured.useRelaxedHTTPSValidation();
        SessionFilter sessionFilter = new SessionFilter();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        final ValidatableResponse userRegisterResponse = RestAssured.given()
                .log().all()
                .filter(sessionFilter)
                .when()
                .contentType(APPLICATION_JSON_VALUE)
                .get("/doc/first")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(APPLICATION_JSON_VALUE);
        final String actualResponse = userRegisterResponse.extract().asString();

        // then
        final String expectedResponse = readSystemResource("tests/DocumentIntegrationTest/get-existing-doc-expected-response.json");
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }
}
