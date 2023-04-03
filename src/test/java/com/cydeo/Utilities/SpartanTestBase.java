package com.cydeo.Utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanTestBase {

    @BeforeAll
    public static void init(){
        baseURI= "http://44.211.151.143:8000";

    }
}
