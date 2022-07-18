package day5;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to MAP")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 15)
                     .when().get("/api/spartans/{id}")
                     .then().statusCode(200).extract().response();


        //get the json and convert it to the map

        Map<String,Object> jsonMap=response.as(Map.class);

        //now giving us java map result
        System.out.println("jsonMap.toString() = " + jsonMap.toString());

        String actualName = (String) jsonMap.get("name");

        System.out.println("actualName = " + actualName);

        assertThat(actualName, is("Meta"));


    }




}
