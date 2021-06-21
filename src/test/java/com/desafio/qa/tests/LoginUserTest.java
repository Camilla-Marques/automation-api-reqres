package com.desafio.qa.tests;

import com.desafio.qa.domain.User;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class LoginUserTest extends BaseConfigurationForTest {
    private static final String LOGIN_USERS_ENDPOINT = "/login";

    @Test
    public void unsuccessfulLogin() {
        User user = new User();
        user.setEmail("peter@klaven");
        given()
                .body(user)
                .when()
                .post(LOGIN_USERS_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", is("Missing password"));

    }

    @Test
    public void sucessfulLogin() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");
        given()
                .body(user)
                .when()
                .post(LOGIN_USERS_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
