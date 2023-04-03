package com.cydeo.Utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class CydeoTestBase {


    @BeforeAll
    public static void init(){
        baseURI= "https://api.training.cydeo.com";

    }
}
