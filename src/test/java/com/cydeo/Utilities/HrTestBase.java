package com.cydeo.Utilities;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;

public class HrTestBase {

    @BeforeAll
    public static void init(){
        baseURI= "http://44.211.151.143:1000/ords/hr";

    }
}
