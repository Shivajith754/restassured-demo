package com.example.apitests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidationTest {

    @BeforeClass
    public void setup() {
        baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void validatePostJsonSchema() {
        given().
                when().
                get("/posts/1").
                then().
                assertThat().
                statusCode(200).
                // load schema from classpath: src/test/resources/schemas/post-schema.json
                        body(matchesJsonSchemaInClasspath("schemas/post-schema.json")).
                log().body();
    }
}
