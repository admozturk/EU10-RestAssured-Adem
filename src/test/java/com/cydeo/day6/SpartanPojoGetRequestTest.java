package com.cydeo.day6;

import com.cydeo.Utilities.SpartanTestBase;
import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).contentType("application/json")
                .log().all()
                .extract().response();

        // deserialize --> from json to pojo
        // we have 2 ways

        // using as() method
        //we convert json response to spartan object with the help of jackson
        //as() method uses jackson to de serialize(converting JSON to Java class)

        Spartan spartan15 = response.as(Spartan.class);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());

        //second way of deserialize json to java
        //2.using JsonPath to deserialize to custom class

        JsonPath jsonPath = response.jsonPath();
        Spartan s15 = jsonPath.getObject("", Spartan.class);

        System.out.println("spartan1.getName() = " + s15.getName());
        System.out.println("spartan1.getPhone() = " + s15.getPhone());


    }

    @DisplayName("Get one spartan from search endpoint result and use POJO")
    @Test
    public void test2() {
        ///spartans/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type Spartan POJO

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200).contentType("application/json")
                .extract().jsonPath();

        Spartan spartan1 = jsonPath.getObject("content[1]", Spartan.class);

        System.out.println("spartan1 = " + spartan1);
        System.out.println("spartan1.getName() = " + spartan1.getName());


    }

    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200).contentType("application/json")
                .extract().response();


        Search searchResult = response.as(Search.class);

        System.out.println(searchResult.getContent().get(0).getName());

    }

    @DisplayName("GET  /spartans/search and save as List<Spartan>")
    @Test
    public void test4(){

        List<Spartan> spartanList =  given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200).contentType("application/json")
                .extract().jsonPath().getList("content", Spartan.class);

        System.out.println(spartanList.get(0).getName());


    }
}