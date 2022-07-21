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
        //spartan and spartanDynamic are holding 2 different objects and inf of objects.
        //they are using sample template which is Spartan class.

        Spartan spartan = new Spartan();

        spartan.setGender("Male");
        spartan.setName("Jamal");
        spartan.setPhone(3334444333L);

        System.out.println("spartanGiven = " + spartan);

        String expectedMessage = "A Spartan is Born!";

//we are creating a dynamic number
// and verifing it later
        int dynamicIDNumber = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                //RESTASSURED LIBRARIY AUTOMATICALLY CONVERTS JAVA MAP TO THE JSON DATA TYPE.
                // THIS IS DONE BY RESTASSURED NOT JACKSON ACTUALLY
                .body(spartan).log().all()    // body(spartan) : converts my request from java object to json type
                .when()
                .post("/api/spartans")
                .then().statusCode(201).contentType("application/json").body("success",is(expectedMessage))
                .extract().jsonPath().getInt("data.id");

        Spartan spartanDynamic = given().accept(ContentType.JSON)
                .and().pathParam("id", dynamicIDNumber)
                .when().get("api/spartans/{id}")
                .then().statusCode(200).log().all().extract().as(Spartan.class);


       System.out.println("spartanCreated = " + spartanDynamic);

        System.out.println("dynamicIDNumber = " + dynamicIDNumber);

        assertThat(spartanDynamic.getId(), is(dynamicIDNumber));

        assertThat(spartanDynamic.getName(), is(spartan.getName()));
        assertThat(spartanDynamic.getGender(), is(spartan.getGender()));
        assertThat(spartanDynamic.getPhone(), is(spartan.getPhone()));


    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod5(){
        //this example we implement serialization with creatin spartan object sending as a request body
        //also implemented deserialization getting the id,sending get request and saving that body as a response

        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("BruceWayne");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);
        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all().extract().as(Spartan.class);

        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));

    }


    //Create one SpartanUtil class
    //create a static method that returns Map<String,Object>
    //use faker library(add as a depedency) to assign each time different information
    //for name,gender,phone number
    //then use your method for creating spartan as a map,dynamically.




}
