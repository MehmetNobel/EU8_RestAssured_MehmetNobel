package day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJsonPath extends SpartanTestBase {


    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).and()
                .when()
                .get("/api/spartans");

        // we storing all the datas as json format.
        JsonPath jsonPath = response.jsonPath();

        //we are now getting all the names from the list via JSON PATH
        System.out.println("jsonPath.getString(\"name\") = " + jsonPath.getString("name"));
        System.out.println("jsonPath.getString(\"gender\") = " + jsonPath.getString("gender"));
        System.out.println("jsonPath.get(\"phone\") = " + jsonPath.get("phone"));

        //response.prettyPrint();


    }





    @Test
    public void test2(){

        Response response = get("/api/spartans");

        response.prettyPrint();

        System.out.println("response.path(\"name[0]\") = " + response.path("name[0]"));

        //we are storing all the datas in a list
        List<String> listPhone=response.path("gender");

        for (String each : listPhone) {
            System.out.println("each = " + each);
        }

    }








}
