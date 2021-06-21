package com.desafio.qa.tests;

import com.desafio.qa.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class UserCreationTest extends BaseConfigurationForTest {

    private static final String CREATE_USERS_ENDPOINT = "/users";
    private static final String UPDATE_USERS_ENDPOINT = "users/{userId}";
    private static final String DELETE_USERS_ENDPOINT = "users/{userId}";

    @Test
    public void creatUserSucess() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Camilla");
        user.put("job", "QA");

        given()
                .body(user)
                .when().
                    post(CREATE_USERS_ENDPOINT)
                .then()
                    .assertThat()
                        .statusCode(HttpStatus.SC_CREATED)
                        .body("name", is("Camilla"))
                        .body("job", is("QA"));
    }

    @Test
    public void updateManyInformations() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Camilla Marques");
        user.put("job", "Datum TI");

        given()
                .pathParam("userId", 2)
                .body(user)
              .when()
                    .put(UPDATE_USERS_ENDPOINT)
              .then()
                .assertThat()
                    .statusCode(HttpStatus.SC_OK)
                    .body("name", is("Camilla Marques"))
                    .body("job", is("Datum TI"));
    }

    @Test
    public void updateSingleInformation() {
        User user = new User();
        user.setName("Camilla da Conceição Marques");
        given()
                .pathParam("userId", 2)
                .body(user)
                .when()
                    .patch(UPDATE_USERS_ENDPOINT)
                .then()
                .assertThat()
                    .statusCode(HttpStatus.SC_OK)
                    .body("name", is("Camilla da Conceição Marques"));
    }

    @Test
    public void delete() {
        given()
                .pathParam("userId", 2)
                .when()
                    .delete(DELETE_USERS_ENDPOINT)
                .then()
                .assertThat()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
    }


}
