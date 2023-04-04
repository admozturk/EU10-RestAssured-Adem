package com.cydeo.Utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanTestBase {

    @BeforeAll
    public static void init(){

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI= "http://3.87.228.6:8000";

        String dbUrl = "jdbc:oracle:thin:@3.87.228.6:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";

        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }


    @AfterAll
    public static void teardown(){

        DBUtils.destroy();
    }
}
