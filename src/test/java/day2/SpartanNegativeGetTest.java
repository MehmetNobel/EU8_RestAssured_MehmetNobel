package day2;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanNegativeGetTest {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init(){
        baseURI="http://35.171.2.97:8000";

    }


    //negative test case

        @DisplayName("GET request to /api/spartans/10")
        @Test
        public void test1(){
            Response response = RestAssured.given().accept(ContentType.XML).when().get("/api/spartans/10");

            //verify status code is 406

            System.out.println("response.statusCode() = " + response.statusCode());

            //response.prettyPrint();
            assertEquals(406,response.statusCode());
            //verify content type
           // assertEquals("application/xml;charset=UTF-8",response.contentType());
        }

    }




        /*//this gives error.
        Response response = given()
                            .accept(ContentType.XML)
                            .when()
                            .get("/api/spartans/10");


       // Response response = get("api/spartans/10");

        //System.out.println("response.contentType() = " + response.contentType());

        assertEquals(406,response.statusCode());

        assertEquals("application/xml;charset=UTF-8", response.contentType());

        //System.out.println("response.statusCode() = " + response.statusCode());

       // response.prettyPrint();

         */
















