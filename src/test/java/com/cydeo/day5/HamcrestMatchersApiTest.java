package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class HamcrestMatchersApiTest {
    /*
          given accept type is Json
          And path param id is 15
          When user sends a get request to spartans/{id}
          Then status code is 200
          And content type is Json
          And json data has following
              "id": 15,
              "name": "Meta",
              "gender": "Female",
              "phone": 1938695106
           */
    @DisplayName("one spartan with hamcrest and chaining")
    @Test
    public void test() {

        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("http://107.21.60.245:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id", equalTo(15),
                        "name", is("Meta"), "gender", is("Female"),
                        "phone", is(1938695106));


    }

    @DisplayName("Get cydeo Teacher request with chaining and matchers ")
    @Test
    public void test2(){

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",10)
                .when()
                .get( "https://api.training.cydeo.com/teacher/{id}")
                .then()
                .statusCode(200).and().contentType("application/json;charset=UTF-8")
                .and()
                .header("transfer-encoding",is("chunked"))
                .and()
                .header("date",notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName",is(equalTo("Porter")))
                .body("teachers[0].lastName",is("Merbeery"))
                .body("teachers[0].gender",equalTo("Male"));



    }

    @DisplayName("Get request to teacher/all and chain ")
    @Test
    public void test3(){

        // verify Ron,dracool, Erik inside all teachers

        given().accept(ContentType.JSON)
                .when().get("https://api.training.cydeo.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Ron","dracool","Erik"));


    }


}
