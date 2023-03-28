package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest {

    // is an annotation equals to
    @BeforeAll
    public static void init(){
        RestAssured.baseURI=  "http://44.211.39.193:1000/ords/hr";
    }

    @DisplayName("Get request to regions")
    @Test
    public void test1(){
        Response response = get("/regions");
        System.out.println("response.statusCode() = " + response.statusCode());


    }

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/regions/2");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Americas"));


    }
}
