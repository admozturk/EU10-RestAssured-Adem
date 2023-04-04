package com.cydeo.day5;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
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

        String actual = (String) jsonMap.get("name");
        assertThat(actual,is("Meta"));


    }

    @DisplayName("get all spartans to java map structure")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().when().get("/api/spartans/")
                .then().statusCode(200)
                .extract().response();

        // starting point(List)                 and ending point(List) must match
        List<Map<String,Object>> allSpartans = response.as(List.class);
        System.out.println(allSpartans.toString());

        System.out.println("allSpartans.get(0).get(\"name\") = " + allSpartans.get(0).get("name"));

        // how to get individual spartan
        Map<String,Object> getThirdSpartan = allSpartans.get(2);
        System.out.println("getThirdSpartan = " + getThirdSpartan);


    }
}