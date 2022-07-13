package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//static method importing
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class HRGetRequests {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://35.171.2.97:1000/ords/hr/";

    }

    @Test
    public void test1(){

        //we imported static method here
        Response response = get("regions");

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();

        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

    }

    @Test
    public void test3(){

        // 1 .way
        /*Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("regions/2");

         */

        // 2 .way
        Response response = get("regions/2");


        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        assertTrue(response.body().asString().contains("Americas"));

        assertEquals(200,response.statusCode());

        assertEquals("application/json", response.contentType());




    }





}
