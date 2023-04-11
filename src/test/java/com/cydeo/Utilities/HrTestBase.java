package com.cydeo.Utilities;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class HrTestBase {

    @BeforeAll
    public static void init(){
        baseURI= "http://54.159.151.19:1000/ords/hr";

        String dbUrl = "jdbc:oracle:thin:@54.159.151.19:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";

        // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }


    @AfterAll
    public static void teardown(){

        //   DBUtils.destroy();
    }

}
