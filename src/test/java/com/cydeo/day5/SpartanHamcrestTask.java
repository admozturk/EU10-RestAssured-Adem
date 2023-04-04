package com.cydeo.day5;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanHamcrestTask extends SpartanTestBase {

    @DisplayName("get spartan search and chain together")
    @Test
    public void test1() {

        // Along with this statement, I want to save names inside the List<String>


        List<String> names = given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "j",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", greaterThanOrEqualTo(3))
                .extract().response().jsonPath().getList("content.name");

        System.out.println(names);

    }
}