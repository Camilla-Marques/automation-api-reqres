package com.desafio.qa.tests;

import com.desafio.qa.domain.User;
import org.apache.http.HttpStatus;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ListDataUserTest extends BaseConfigurationForTest {

    private static final String LIST_USERS_ENDPOINT = "/users";
    private static final String LIST_SINGLE_USERS_ENDPOINT = "users/{userId}";
    private static final String LIST_NOT_FOUND_SINGLE_USERS_ENDPOINT = "users/{userId}";
    private static final String DELAY_RESPONSE_ENDPOINT = "/users?delay=3";

    @Test
    public void listUsers() {
        given().
                params("page","2").
            when().
                get(LIST_USERS_ENDPOINT).
            then().
                statusCode(HttpStatus.SC_OK).
                body(
                 "page", is(2),
                    "data", is(notNullValue())
                );
    }


    @Test
    public void listSingleUser() {
        User user = given()
            .pathParam("userId", 2)
        .when()
             .get(LIST_SINGLE_USERS_ENDPOINT)
        .then()
             .statusCode(HttpStatus.SC_OK)
        .extract()
             .body().jsonPath().getObject("data", User.class);

        assertThat(user.getEmail(), containsString("@reqres.in"));
        assertThat(user.getName(), is("Janet"));
        assertThat(user.getLastname(), is("Weaver"));
    }


    @Test
    public void listNotFoundSingleUser() {
        given()
                .pathParam("userId", 23)
                .when()
                .get(LIST_NOT_FOUND_SINGLE_USERS_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void listNumberOfUsers() {
        int pageUsers = 2;
        int perPageExpected = returnPerPageExpected(2);

        given().
                params("page",pageUsers).
             when().
                get(LIST_USERS_ENDPOINT).
             then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "page", is(pageUsers),
                        "data.size()", is(perPageExpected),
                        "data.findAll { it.avatar.startsWith('https://reqres.in/') }. size()", is(perPageExpected)
                );
    }

    private int returnPerPageExpected(int page) {
        int perPageExpected = given()
                .param("page", page)
            .when()
                .get(LIST_USERS_ENDPOINT)
            .then()
                .statusCode(HttpStatus.SC_OK)
            .extract()
                .path("per_page");
        return perPageExpected;
    }

    @Test
    public void ResponseTimeValidation () {
        given()
        .when()
            .get(DELAY_RESPONSE_ENDPOINT)
        .then()
            .time(lessThan(5000L))
            .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }


}


