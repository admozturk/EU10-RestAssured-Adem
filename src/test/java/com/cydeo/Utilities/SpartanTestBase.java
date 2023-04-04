package com.cydeo.Utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanTestBase {

    @BeforeAll
    public static void init(){
        baseURI= "http://3.87.228.6:8000";

    }
}
