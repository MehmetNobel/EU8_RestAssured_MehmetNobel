package day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to one Spartan for update with map")
    @Test
    public void PUTRequest() {

        Map<String, Object> putRequestMap = new LinkedHashMap<>();

        putRequestMap.put("name", "ahmet37");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 8879876451L);
        //WE CAN EİTHER PASS MAP OR STR INSIDE THE BODY.
        String str = "{\n" +
                "    \"id\": 137,\n" +
                "    \"name\": \"ahmet40\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 8879876451\n" +
                "}";


        given() // .accept(ContentType.JSON)  since we will not get any json body, 204(no body)
                //so there is no need to tell accept type to api
                .contentType(ContentType.JSON)
                .body(str).log().all()// we can either pass String or map or pojo class
                .pathParam("id", 137)// there is serialization here
                .when().put("api/spartans/{id}")
                .then().statusCode(204);

        String string = given().accept(ContentType.JSON).pathParam("id", 137)
                .get("api/spartans/{id}").jsonPath().getString("name");

        // we retrieving the data after updating.
        System.out.println("string = " + string);


    }

    @DisplayName("PUT request to one Spartan for partial update with map")
    @Test
    public void PATCHRequest() {

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        // we are just giving one parameter
        //since in patch we are able to give just one parameter
        //but in put we must provide all fields.
        putRequestMap.put("name", "veli37");
        // putRequestMap.put("gender", "Male");
        //putRequestMap.put("phone", 8879876451L);

        String str = "{\n" +
                "    \"id\": 137,\n" +
                "    \"name\": \"ahmet40\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 8879876451\n" +
                "}";


        given() // .accept(ContentType.JSON)  since we will not get any json body, 204(no body)
                //so there is no need to tell accept type to api
                .contentType(ContentType.JSON)
                .body(putRequestMap).log().all()// we can either pass String or map or pojo class
                .pathParam("id", 137)// there is serialization here
                .when().patch("api/spartans/{id}")
                .then().statusCode(204);

        String string = given().accept(ContentType.JSON).pathParam("id", 137)
                .get("api/spartans/{id}").jsonPath().getString("name");

        // we retrieving the data after updating.
        System.out.println("string = " + string);


    }
 @DisplayName("DELETE request to one Spartan ")
    @Test
    public void DELETERequest() {

        int idToDelete=131;

        given() .log().all()// we can either pass String or map or pojo class
                .pathParam("id", idToDelete)// there is serialization here
                .when().delete("api/spartans/{id}")
                .then().statusCode(204);

       /*given().pathParam("id", idToDelete).log().all()
             .get("api/spartans/{id}").then().statusCode(404);


        */

    }


}
