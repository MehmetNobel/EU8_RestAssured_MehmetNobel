package day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HamcrestMatchersApiTest {

    @DisplayName("one spartan with Hamcrest and chaining")
    @Test
    public void test1(){
             // 1 part : request
                 given()
                        .accept(ContentType.JSON)
                        .and().pathParam("id",15)
                 .when()
                         .get("http://35.171.2.97:8000/api/spartans/{id}")

                 // 2.part assertion part
                  .then()
                         .statusCode(200)
                         .and().assertThat()
                         .contentType("application/json")
                         .and()
                  // we are using hamcret here
                         .body("id", equalTo(15), "name", is("Meta"),
                                 "gender", is("Female"), "phone", is(1938695106));

    }

    @DisplayName("one spartan with Hamcrest and chaining")
    @Test
    public void test2(){
        // 1 part : request
         given()
                .accept(ContentType.JSON)
                .and().pathParam("id",21902)
        .when()
                .get("http://api.cybertektraining.com/teacher/{id}")

                // 2.part assertion part  since we are performing the assertion we dont need response body
         .then()
                .statusCode(200)
                .and()  // sytetic sugar ; we might not write this at all
                .contentType("application/json")
                .and()
                 .header("Date", notNullValue())
                // we are using hamcret here and perfing the assertions
                .body("teachers[0].teacherId", is(21902))
                .body("teachers[0].firstName", is("Leoneli"))
                .body("teachers[0].lastName", is("Messi"))
                .body("teachers[0].emailAddress", is("LeonelMessi@Gmail.com"));

    }
    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void test3(){
        // 1 verify that we have Leonel, Andrii  inside the teacher names
        given()
                .accept(ContentType.JSON)
                .and()
        .when()
                .get("http://api.cybertektraining.com/teacher/all")

                // 2.part assertion part  since we are performing the assertion we dont need response body
         .then()
                .statusCode(200)
                .and()  // sytetic sugar ; we might not write this at all
                .contentType("application/json")
                .and()
                 .header("Date", notNullValue())
                // we are creating a list and searching result inside the list.
                //.body("teachers.firstName"   ==>> returns us a collection
                .body("teachers.firstName", hasItems("Leoneli", "Andrii"));


    }






}
