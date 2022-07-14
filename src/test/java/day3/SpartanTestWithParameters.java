package day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestWithParameters {


    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init(){
        baseURI="http://35.171.2.97:8000";

    }

    @Test
    public void test1(){
                            //in here we ignored RestAssured word since we staticly get the method

        Response response = get("/api/spartans/5");

        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());

        boolean blythe = response.body().asString().contains("Blythe");

        System.out.println("blythe = " + blythe);


    }

    @Test
    public void test2(){

        //and() : increase readebility, nothing else.
        Response response = given().accept(ContentType.JSON)
                                    .and().pathParam("id", 5)
                            .when()
                                     .get("/api/spartans/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Blythe"));

        response.prettyPrint();

    }

    @Test
    public void test3(){

        //and() : increase readebility, nothing else.
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 150)
                .when()
                .get("/api/spartans/{id}");

        //System.out.println("response.statusCode() = " + response.statusCode());

        boolean not_found = response.body().asString().contains("Not Found");

        System.out.println("not_found = " + not_found);

        //assertEquals(404,response.statusCode());

        //assertEquals("application/json",response.contentType());

        //assertTrue(response.body().asString().contains("Blythe"));

        //response.prettyPrint();

    }
    @DisplayName("GET request /api/spartans/{id} Negative Test")
    @Test
    public void test4(){
        //pathparameters
        Response response = given().accept(ContentType.JSON).log().all()
                .pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");

        //verify status code 404
        assertEquals(404,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());
        //verify NotFound in the json payload/body
        assertTrue(response.body().asString().contains("Not Found"));

    }

     /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @Test
    public void test5(){
        //queryparameters    // log all gives us all the details of the request
        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "female")
                .when()
                .get("api/spartans/search");


        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));


    }

    @Test
    public void test6(){
        //queryparameters    // log all gives us all the details of the request
        //we defined in a map and used map overloaded method.
        Map<String,String > map1=new HashMap<>();

        map1.put("nameContains","e");
        map1.put("gender","female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(map1)
                .when()
                .get("api/spartans/search");


        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        assertEquals(200,response.statusCode());

        response.prettyPrint();

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));



    }







}
