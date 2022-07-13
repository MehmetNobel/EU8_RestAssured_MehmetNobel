package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class SpartanNegativeTestCase {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init(){
        baseURI="http://35.171.2.97:8000";

    }



    @Test
    public void test1(){

        //Response response = RestAssured.get("/api/spartans");

        Response response = RestAssured.given()
                .accept(ContentType.XML).when().get("/api/spartans/10");

        //Assertions.assertEquals(406,response.statusCode());

        System.out.println("response.getContentType() = " + response.getContentType());
       System.out.println("response.getStatusCode() = " + response.getStatusCode());
    }


}
