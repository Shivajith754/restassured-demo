package com.example.apitests;

import org.testng.annotations.Test;

public class SanityTest {
    @Test
    public void testSetup() {
        System.out.println("REST-Assured environment ready!");
    }
}
