package com.cydeo.day5;

import com.cydeo.Utilities.DBUtils;
import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import java.util.Map;

public class SpartanApiVsDB extends SpartanTestBase {
    @DisplayName("GET one spartan from Api database")
    @Test
    public void testDB1(){

        //get id,name,gender phone  from database
        //get same information from api
        //compare

        //1. get id,name,gender phone  from database

        String query1 ="select spartan_id,name,gender,phone from spartans\n" +
                "where spartan_id=15";

        // save data inside map
        Map<String,Object> dbMap = DBUtils.getRowMap(query1);
        System.out.println(dbMap);

        //get same information from api
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then().statusCode(200).contentType("application/json")
                .extract().response();


         //Deserialization here JSon to Java  with Jackson objectMapper
        Map<String, Object> apiMap = response.as(Map.class);

        System.out.println(apiMap);

        // compare database with api --> with jackson object mapper

        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("Name"),is(equalTo(dbMap.get(" NAME"))));
        assertThat(apiMap.get("gender"),equalTo(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));






    }
}
