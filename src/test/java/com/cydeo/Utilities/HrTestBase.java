package com.cydeo.Utilities;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;

public class HrTestBase {

    @BeforeAll
    public static void init(){
        baseURI= "http://44.211.172.161:1000/ords/hr";

    }
}
