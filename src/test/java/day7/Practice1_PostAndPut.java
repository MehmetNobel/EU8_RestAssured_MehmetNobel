package day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class Practice1_PostAndPut extends SpartanTestBase {


    @DisplayName("post and put")
    @Test
    public void test1(){

        String str = "{\n" +
                "    \"id\": 138,\n" +
                "    \"name\": \"ahmet40\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 8879876451\n" +
                "}";


        Response response = given().accept(ContentType.JSON).log().all()
                .contentType(ContentType.JSON)
                .body(str).when().post("api/spartans")
                .then().statusCode(201)
                .extract().response();

        response.prettyPrint();

        String str2 = "{\n" +
                "    \"id\": 138,\n" +
                "    \"name\": \"ahmet41\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 8879876451\n" +
                "}";


              given().accept(ContentType.JSON).log().all()
                .contentType(ContentType.JSON).pathParam("id", 138)// there is serialization here
                .body(str2)
                .put("api/spartans/{id}")
                .then().statusCode(204);


            given().get("api/spartans").prettyPrint();




    }


}
