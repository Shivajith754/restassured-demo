package com.example.apitests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CrudOperationsTest {

    @BeforeClass
    public void setup() {
        // reuse the base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testCreatePost() {
        given().
                header("Content-Type", "application/json; charset=UTF-8").
                body("{ \"userId\": 10, \"title\": \"foo\", \"body\": \"bar\" }").
                when().
                post("/posts").
                then().
                assertThat().
                statusCode(201).                    // Created
                body("id", notNullValue()).         // API assigns a new id
                body("userId", equalTo(10)).
                body("title",  equalTo("foo")).
                body("body",   equalTo("bar")).
                log().body();                       // print the created JSON
    }

    @Test(dependsOnMethods = "testCreatePost")
    public void testUpdatePost() {
        given().
                header("Content-Type", "application/json; charset=UTF-8").
                body("{ \"id\": 1, \"userId\": 1, \"title\": \"updated title\", \"body\": \"updated body\" }").
                when().
                put("/posts/1").
                then().
                assertThat().
                statusCode(200).                    // OK
                body("id",    equalTo(1)).
                body("title", equalTo("updated title")).
                body("body",  equalTo("updated body")).
                log().body();
    }

    @Test(dependsOnMethods = "testUpdatePost")
    public void testDeletePost() {
        when().
                delete("/posts/1").
                then().
                assertThat().
                statusCode(200);                    // OK, empty response
    }
}
