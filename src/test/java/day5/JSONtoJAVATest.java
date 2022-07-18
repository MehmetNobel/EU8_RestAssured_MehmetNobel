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

    @DisplayName("GET all Spartans and deserialize to MAP")
    @Test
    public void allSpartansToMap(){

        Response response = given()
                            .when().get("/api/spartans")
                            .then().statusCode(200).extract().response();


        //get the json and convert it to the map

        //since we have lots of maps inside the json then we will hold these datas on list of map

        List<Map<String,Object>> jsonList=response.as(List.class);

        String name = (String) jsonList.get(0).get("name");

        System.out.println("name = " + name);

        System.out.println("jsonList.get(2).get(\"phone\") = " + jsonList.get(2).get("phone"));

        //we are just retrieving the first map from the list.
        Map<String, Object> oneMap = jsonList.get(0);

        System.out.println("oneMap = " + oneMap);


    }





}
