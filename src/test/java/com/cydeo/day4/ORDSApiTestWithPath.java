package com.cydeo.day4;

import com.cydeo.Utilities.HrTestBase;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

public class ORDSApiTestWithPath extends HrTestBase {

    @DisplayName("get request to countries with path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\": 2}")
                .when().get("/countries");


        assertEquals(200, response.statusCode());

        // print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        // print the first country_id

        String countryId = response.path("items[0].country_id");
        System.out.println("countryId = " + countryId);


        // print 2nd country_name

        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // print 3rd countries "http://44.211.151.143:1000/ords/hr/countries/CA"

        String thirdCountryIp = response.path("items[2].links[0].href");
        System.out.println("thirdCountryIp = " + thirdCountryIp);

        // get all the country names

        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        // assert that all regions id are equal to 2

        List<Integer> allRegionIDs = response.path("items.region_id");

        for (Integer allRegionID : allRegionIDs) {
            assertEquals(2, allRegionID);

        }


    }

    @DisplayName("GET request to / employees working as ad press with query parameters")
    @Test
    public void test2() {

        Response response = given().log().all().accept(ContentType.JSON).and()
                .queryParam("q", "{ \"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.header("Content-Type"));

        assertTrue(response.body().asString().contains("IT_PROG"));

        List<String> allJObIds = response.path("items.job_id");
        for (String allJObId : allJObIds) {
            System.out.println("allJObId = " + allJObId);
            assertEquals("IT_PROG",allJObId);

        }

        // Task print each name of it programmers

        List<String> employersName = response.path("items.first_name");

        for (String eachNames : employersName) {


        }

    }
}