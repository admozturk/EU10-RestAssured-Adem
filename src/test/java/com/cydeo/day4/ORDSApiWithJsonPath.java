package com.cydeo.day4;
import com.cydeo.Utilities.HrTestBase;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.collections4.Get;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.stylesheets.LinkStyle;
import java.util.ArrayList;
import java.util.List;


public class ORDSApiWithJsonPath extends HrTestBase {

    @DisplayName("Get request to countries")
    @Test
    public void test1() {
        Response response = get("/countries");

        // get the json path
        JsonPath jsonPath = response.jsonPath();

        // get the second country name

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // get all country id's

        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);

        //get all country names where their region id is equal to 2

        List<String> allCountryNamesRegionId2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println(allCountryNamesRegionId2);


    }

    @DisplayName("Get request to employees with query param")
    @Test
    public void test2() {
        Response response = get("/employees");

        given().queryParam("limit",107).when().get("/employees");

        // get me all emails of employees who is working in IT_PROG.

        JsonPath jsonPath = response.jsonPath();

       List<String> employeeItProgs =  jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeItProgs);

      //  Get me first name of employees who is making more than 10000

        List<String> employeeFirstName = jsonPath.getList("items.findAll{it.salary > 10000}.first_name");

        System.out.println(employeeFirstName);

        // get the max salary first_Name
        String kingFirstName = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(kingFirstName);

    }
}