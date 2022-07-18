package day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanHamcrestTest extends SpartanTestBase {

    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test1() {

        //exract is a magical word to retrieve the response

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "j", "gender", "Male")
                .when().log().all()   //==>> log all to get all the headers
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and().log().all()
                .body("totalElement", is(3)).extract().response();

        response.prettyPrint();

        List<String> names = response.path("content.name");

        System.out.println("names = " + names);


    }

    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test2() {

        //exract is a magical word to retrieve the response
        // CHAIN  + RESPONSE

        int statusCode = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "j", "gender", "Male")
                .when().log().all()   //==>> log all to get all the headers
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and().log().all()
                .body("totalElement", is(3)).extract().statusCode();

        System.out.println("statusCode = " + statusCode);


    }


}
