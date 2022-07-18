package day5;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;
import utilities.HRTestBase;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET Request to employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest(){

        //checking according to a given list.

        List<String> names= Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        // 1 part : request
        given()
                .accept(ContentType.JSON)
                .and()
                .when().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .get(ConfigurationReader.getProperty("hrUrl")+"employees")

                // 2.part assertion part  since we are performing the assertion we dont need response body
                .then()
                .statusCode(200)
                .and()  // syntetic sugar ; we might not write this at all
                .contentType("application/json")
                .and()
                .header("Date", notNullValue())
                // we are using hamcret here and perfing the assertions
                // "items.first_name"  ==>> returning a list to me. then i assert with hasItems method.
                .body("items.first_name", hasItems("Alexander"))
                .body("items.first_name", hasItems("Bruce"))
                .body("items.first_name", hasItems("David"))
                .body("items.first_name", hasItems("David"))

                //does every item equal to IT_PROG ??
                .body("items.job_id", everyItem(equalTo("IT_PROG")))

                //does every item in the relative order ?
                .body("items.first_name", containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"))

               //every item in any order
                .body("items.first_name", containsInAnyOrder("Diana","Alexander","Bruce","David","Valli"))

                //checking the exactly the given above list
                .body("items.first_name",equalTo(names));


    }

    @DisplayName("CHAIN AND RESPONSE BOTH")
    @Test
    public void employeesTest2(){

        //checking according to a given list.

        List<String> names= Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        // 1 part : request
        Response response = given()
                .accept(ContentType.JSON)
                .and().log().all()   // log all gives us all the request inf
                .when().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .get(ConfigurationReader.getProperty("hrUrl") + "employees")

                // 2.part assertion part  since we are performing the assertion we dont need response body
                .then()
                .statusCode(200)
                .and()  // syntetic sugar ; we might not write this at all
                .contentType("application/json")
                .and()
                .header("Date", notNullValue())
                // we are using hamcret here and perfing the assertions
                // "items.first_name"  ==>> returning a list to me. then i assert with hasItems method.
                .body("items.first_name", hasItems("Alexander"))
                .body("items.first_name", hasItems("Bruce"))
                .body("items.first_name", hasItems("David"))
                .body("items.first_name", hasItems("David"))
                .and()
                .log().all()    // give us all the response inf
                //does every item equal to IT_PROG ??
                .body("items.job_id", everyItem(equalTo("IT_PROG")))

                //does every item in the relative order ?
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"))

                //every item in any order
                .body("items.first_name", containsInAnyOrder("Diana", "Alexander", "Bruce", "David", "Valli"))

                //checking the exactly the given above list
                .body("items.first_name", equalTo(names)).extract().response();
        //we are getting the response with extract method
                                            // it can be normal response or json response.


        //we can implement assertThat later on.

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getList("items.first_name"),hasSize(5));


    }







}
