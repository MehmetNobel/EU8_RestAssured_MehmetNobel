package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    //restassured is a flexible library, we can use many of choices.
    //when().get()  or directly get()

    String baseUrl = "http://35.171.2.97:8000/";

    @Test
    public void test1() {

        //to give a content type we write contentType.Json
        //we would go on with just get(url) but in this case we can not specify content type
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());

        response.prettyPrint();

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("application/json", response.contentType());


    }

    @Test
    public void test2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "api/spartans/3");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("application/json", response.contentType());

        //assert that if the body contains name Fidole or not
        //this is converting the json text to the java with ==>>asString method.
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }

    @DisplayName("get request to api hello")
    @Test
    public void test3() {

        Response response = RestAssured.get(baseUrl + "api/hello");

        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();

        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        //we are checking the headers here.
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //we are getting date of response header.
        System.out.println("response.header(\"Date\") = " +
                response.header("Date"));

        //we are getting content type of response header
        System.out.println("response.header(\"Content-Length\") = " +
                response.header("Content-Length"));

        System.out.println("response.header(\"Keep-Alive\") = " + response.header("Keep-Alive"));


    }


}
