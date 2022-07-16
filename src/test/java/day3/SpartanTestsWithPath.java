package day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestsWithPath {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init(){
        baseURI="http://35.171.2.97:8000";

    }

    @DisplayName("Get one spartan with path method")
    @Test
    public void test1(){

        //PATH PARAMETER

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 10)
                .when()
                .get("/api/spartans/{id}");

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        response.prettyPrint();

        boolean lorenza = response.body().asString().contains("Lorenza");

        System.out.println("lorenza = " + lorenza);

        assertEquals("application/json",response.header("Content-Type"));


    }





}
