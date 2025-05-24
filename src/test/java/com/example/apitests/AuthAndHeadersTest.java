package com.example.apitests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthAndHeadersTest {

    @BeforeClass
    public void setup() {
        // point all calls to httpbin
        RestAssured.baseURI = "https://httpbin.org";
    }

    @Test
    public void testBasicAuth() {
        given().
                auth().basic("user", "passwd").
                when().
                get("/basic-auth/user/passwd").
                then().
                assertThat().
                statusCode(200).
                body("authenticated", equalTo(true)).
                body("user",          equalTo("user")).
                log().body();
    }

    @Test
    public void testBearerAuth() {
        given().
                auth().oauth2("my-secret-token").
                when().
                get("/bearer").
                then().
                assertThat().
                statusCode(200).
                body("authenticated", equalTo(true)).
                body("token",         equalTo("my-secret-token")).
                log().body();
    }

    @Test
    public void testCustomHeader() {
        given().
                header("X-Custom-Header", "TestValue").
                when().
                get("/headers").
                then().
                assertThat().
                statusCode(200).
                // JSON path: headers."X-Custom-Header"
                        body("headers.'X-Custom-Header'", equalTo("TestValue")).
                log().body();
    }
}
