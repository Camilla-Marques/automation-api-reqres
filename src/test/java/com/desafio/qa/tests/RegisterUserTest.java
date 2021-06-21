package com.desafio.qa.tests;

import com.desafio.qa.domain.User;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RegisterUserTest extends BaseConfigurationForTest {

    private static final String REGISTER_USERS_ENDPOINT = "/register";


    @Test
    public void successfulRegistration() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");
        given()
                .body(user)
                .when()
                    .post(REGISTER_USERS_ENDPOINT)
                .then()
                    .assertThat()
                        .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void unsuccessfulRegistration() {
        User user = new User();
        user.setEmail("sydney@fife");
        given()
            .body(user)
            .when()
                .post(REGISTER_USERS_ENDPOINT)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", is("Missing password"));

    }

}

