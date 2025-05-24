package com.example.apitests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetCommentsTest {

    @BeforeClass
    public void setup() {
        // reuse the same base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testCommentsWithQueryParam() {
        given().
                queryParam("postId", 1).
                when().
                get("/comments").
                then().
                assertThat().
                statusCode(200).
                // every “postId” in the returned array must equal 1
                        body("postId", everyItem(equalTo(1))).
                // print the JSON array
                        log().body();
    }

    @Test
    public void testCommentsWithPathParam() {
        given().
                pathParam("postId", 1).
                when().
                get("/posts/{postId}/comments").
                then().
                assertThat().
                statusCode(200).
                body("postId", everyItem(equalTo(1))).
                log().body();
    }
}
