package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url="http://35.171.2.97:8000/api/spartans";

    @Test
    public void test1(){

        //sending request
        Response response = RestAssured.get(url);

        //writing the response type
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing the message.
        response.prettyPrint();

        //we can add these
        //Assertions.assertEquals(response.statusCode(),200);

        //Assertions.assertEquals(response.contentType(),"application/json");



    }




}
