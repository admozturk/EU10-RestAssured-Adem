package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

public class SpartanGetRequest {

    String baseUrl = "http://44.211.39.193:8000";
    // Given then Accept type application/json
    // When user send GET request to api/spartans end point
    // Then status code must be 200
    // Then response Content Type must be application/json
    // And response body should include spartan result


    @Test
    public void test1() {

        // given access Accept type application
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans/");

        // printing status code from request object
        System.out.println("response.statusCode() = " + response.statusCode());

        // printing content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        // print all result body
        response.prettyPrint();

        // verify status code is 200
        Assertions.assertEquals(response.statusCode(), 200);

        // verift content type application json
        Assertions.assertEquals(response.contentType(), "application/json");


    }

    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans/3");

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("application/json", response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Fidole"));


    }

    @Test
    public void test3() {

        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        Assertions.assertEquals("17", response.header("Content-Length"));

        Assertions.assertEquals("Hello from Sparta", response.body().asString());

    }
}
