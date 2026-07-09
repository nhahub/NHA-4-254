package org.example.utils;

import io.restassured.RestAssured;

public class ApiClient {

    static {
        RestAssured.baseURI =
                "https://opensource-demo.orangehrmlive.com";
    }
}