package com.example.apitests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Simple auth + header checks against httpbin.org.
 *
 * NB: httpbin keeps the header name’s exact case, so use the same spelling
 *     you send when you assert the response JSON.
 */
public class AuthAndHeadersTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://httpbin.org";
    }

    @Test
    public void testBasicAuth() {
        given()
                .auth().basic("user", "passwd")
                .when()
                .get("/basic-auth/user/passwd")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .body("user",          equalTo("user"))   // <- expect whatever username you sent
                .log().body();
    } //redeploy

    @Test
    public void testBearerAuth() {
        given()
                .auth().oauth2("my-secret-token")
                .when()
                .get("/bearer")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .body("token",         equalTo("my-secret-token"))
                .log().body();
    }

    @Test
    public void testCustomHeader() {
        given()
                .header("X-Custom-Header", "TestValue")
                .when()
                .get("/headers")
                .then()
                .statusCode(200)
                .body("headers.'X-Custom-Header'", equalTo("TestValue"))  // <- exact case!
                .log().body();
    }
}
