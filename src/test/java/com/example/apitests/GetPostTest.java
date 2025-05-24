package com.example.apitests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetPostTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetPostById() {
        given().
                        when().
                get("/posts/1").
                then().
                assertThat().
                statusCode(200).                   // HTTP 200 OK
                body("id", equalTo(1)).           // JSON field id == 1
                body("userId", equalTo(1)).       // JSON field userId == 1
                body("title", notNullValue()).    // title is not null
                log().body();                     // print full JSON for inspection
    }
}
