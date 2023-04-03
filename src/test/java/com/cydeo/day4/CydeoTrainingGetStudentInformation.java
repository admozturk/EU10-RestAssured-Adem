package com.cydeo.day4;

import com.cydeo.Utilities.CydeoTestBase;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CydeoTrainingGetStudentInformation extends CydeoTestBase {
    @DisplayName("Get request to an individual student")
    @Test
    public void test1(){

        // send a get request to student id 23401 as a path parameter
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",37)
                .when().get("/student/{id}");

        // verify status code / content type, transfer-encoding== envoy

        // verify status code
        assertEquals(200,response.statusCode());
        // verify content type
        assertEquals("application/json;charset=UTF-8",response.contentType());
        // verify transfer-encoding== envoy
        assertEquals("chunked",response.header("transfer-encoding"));
        // verify date header exist
        assertTrue(response.headers().hasHeaderWithName("date"));

        JsonPath jsonPath = response.jsonPath();

        // Assert firstName
        assertEquals("Dorothea",jsonPath.getString("students[0].firstName"));
        // Assert batch
        assertEquals(3,jsonPath.getInt("students[0].batch"));
        // Assert section
        assertEquals("N/A",jsonPath.getString("students[0].section"));
        // Assert email
        assertEquals("cgladwin3@unicef.org",jsonPath.getString("students[0].contact.emailAddress"));
        // Assert company name
        assertEquals("Quire",jsonPath.getString("students[0].company.companyName"));
        // Assert state
        assertEquals("Louisiana", jsonPath.getString("students[0].company.address.state"));
        // Assert zipcode
        assertEquals(16581,jsonPath.getInt("students[0].company.address.zipCode"));

    }

}
