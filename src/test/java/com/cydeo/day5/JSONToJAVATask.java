package com.cydeo.day5;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class JSONToJAVATask extends SpartanTestBase {

    @DisplayName("get 1 spartan and deserialize to map")
    @Test
    public void test1() {

        Response response = given().pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        // get the json convert to map

        Map<String,Object> jsonMap = response.as(Map.class);
        System.out.println(jsonMap.toString());


    }
}