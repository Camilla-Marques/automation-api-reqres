package com.desafio.qa.tests;

import com.desafio.qa.domain.Resources;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListDataResourcesTest extends BaseConfigurationForTest{

    private static final String LIST_RESOURCES_ENDPOINT = "/unknowm";
    private static final String LIST_SINGLE_RESOURCES_ENDPOINT = "unknowm/{resourceId}";
    private static final String LIST_SINGLE_RESOURCES_NOT_FOUND_ENDPOINT = "unknowm/{resourceId}";

    @Test
    public void listResources() {
        given().
                params("page","1").
        when().
                get(LIST_RESOURCES_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "page", is(1),
                        "data", is(notNullValue())
                );
    }

    @Test
    public void listSingleResource() {
        Resources resources = given()
                .pathParam("resourceId", 2)
        .when()
                .get(LIST_SINGLE_RESOURCES_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().jsonPath().getObject("data", Resources.class);

        assertThat(resources.getName(), is("fuchsia rose"));
        assertThat(resources.getYear(), is("2001"));
        assertThat(resources.getPantoneValue(), is("17-2031"));
    }

    @Test
    public void listNotFoundSingleResource() {
        given()
                .pathParam("resourceId", 23)
        .when()
                .get(LIST_SINGLE_RESOURCES_NOT_FOUND_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
