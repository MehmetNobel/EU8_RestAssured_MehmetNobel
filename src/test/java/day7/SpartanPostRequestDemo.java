package day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utilities.SpartanTestBase;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


/*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

public class SpartanPostRequestDemo extends SpartanTestBase {


    @Test
    public void postMethod1() {

        String requestJsonBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Tuna\",\n" +
                "  \"phone\": 1234567890\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        //veriy that status code is 201
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        //getting all the spartans after creating new one.
        //Response response1 = given().get("api/spartans");

        //response1.prettyPrint();

        String expectedMessage = "A Spartan is Born!";

        assertThat(response.path("success"), is(expectedMessage));

        assertThat(response.path("data.name"), is("Tuna"));

        assertThat(response.path("data.gender"), is("Male"));

        //since phone data type is long we define as long here
        assertThat(response.path("data.phone"), is(1234567890));

    }

    //SERIALIZATION   JAVA TO JSON FORMAT
    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2() {

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();

        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("name", "Ahmet");
        requestJsonMap.put("phone", 4444555551L);


        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                //RESTASSURED LIBRARIY AUTOMATICALLY CONVERTS JAVA MAP TO THE JSON DATA TYPE.
                // THIS IS DONE BY RESTASSURED NOT JACKSON ACTUALLY
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        //veriy that status code is 201
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        //getting all the spartans after creating new one.
        //Response response1 = given().get("api/spartans");

        //response1.prettyPrint();

        String expectedMessage = "A Spartan is Born!";

        assertThat(response.path("success"), is(expectedMessage));

        assertThat(response.path("data.name"), is("Ahmet"));

        assertThat(response.path("data.gender"), is("Male"));

        //since phone data type is long we define as long here
        assertThat(response.path("data.phone"), is(4444555551L));

        response.prettyPrint();

    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3() {

        //we are using spartan object to create new post request
        Spartan spartan = new Spartan();

        spartan.setGender("Female");
        spartan.setName("Ayse");
        spartan.setPhone(3334444333L);

        System.out.println("spartan = " + spartan);


        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                //RESTASSURED LIBRARIY AUTOMATICALLY CONVERTS JAVA MAP TO THE JSON DATA TYPE.
                // THIS IS DONE BY RESTASSURED NOT JACKSON ACTUALLY
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //veriy that status code is 201
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        //getting all the spartans after creating new one.
        //Response response1 = given().get("api/spartans");

        //response1.prettyPrint();

        String expectedMessage = "A Spartan is Born!";

        assertThat(response.path("success"), is(expectedMessage));

        assertThat(response.path("data.name"), is("Ayse"));

        assertThat(response.path("data.gender"), is("Female"));

        //since phone data type is long we define as long here
        assertThat(response.path("data.phone"), is(3334444333L));

        response.prettyPrint();

    }
 @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod4() {

        //we are using spartan object to create new post request
        Spartan spartan = new Spartan();

        spartan.setGender("Female");
        spartan.setName("Ayse");
        spartan.setPhone(3334444333L);

        System.out.println("spartan = " + spartan);


        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                //RESTASSURED LIBRARIY AUTOMATICALLY CONVERTS JAVA MAP TO THE JSON DATA TYPE.
                // THIS IS DONE BY RESTASSURED NOT JACKSON ACTUALLY
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //veriy that status code is 201
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        //getting all the spartans after creating new one.
        //Response response1 = given().get("api/spartans");

        //response1.prettyPrint();

        String expectedMessage = "A Spartan is Born!";

        assertThat(response.path("success"), is(expectedMessage));

        assertThat(response.path("data.name"), is("Ayse"));

        assertThat(response.path("data.gender"), is("Female"));

        //since phone data type is long we define as long here
        assertThat(response.path("data.phone"), is(3334444333L));

        response.prettyPrint();

    }


}
