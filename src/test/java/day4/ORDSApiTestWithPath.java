package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HRTestBase;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("get all the employees")
    @Test
    public void test1(){

        Response response = get("/employees");

        //to retrieve the first item's name.
        System.out.println("response.path(\"first_name[0]\") = " + response.path("items[0].first_name"));


        //response.prettyPrint();

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        System.out.println("response.contentType() = " + response.contentType());

    }

    @DisplayName("get all the Countries")
    @Test
    public void test2(){

        Response response = get("/countries");

        //to retrieve just the country names
        System.out.println("response.path(\"items.country_name\") = " + response.path("items.country_name"));

        System.out.println("response.path(\"items[4].links[0].href\") = " + response.path("items[4].links[0].href"));

        response.prettyPrint();

        // items[4] gives the element on the 4. index and then we retrieve the related data
        System.out.println("response.path(\"items[4].country_id\") = " + response.path("items[4].country_id"));

        //5. country name
        System.out.println("response.path(\"items[4].country_name\") = " + response.path("items[4].country_name"));

        //2. country name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        System.out.println("response.contentType() = " + response.contentType());

    }

    @DisplayName("get all the Countries based on request type")
    @Test
    public void test3(){

        //response has 3 main parts: body, header, status code

       Response response = given().accept(ContentType.JSON).when()
                .and().queryParam("q", "{\"region_id\": 2}")
                .get("countries");

       // how to write limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print has more
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        response.prettyPrint();

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        System.out.println("response.contentType() = " + response.contentType());

    }

    @DisplayName("get all the region id=2")
    @Test
    public void test4() {

        Response response = given().accept(ContentType.JSON).when()
                .and().queryParam("q", "{\"region_id\": 2}").get("countries");

        response.prettyPrint();

        List<Integer> region_ids = response.path("items.region_id");

        for (Integer each : region_ids) {

            assertTrue(each == 2);

        }
    }
        @DisplayName("Verify all the IT_PROG")
        @Test
        public void test5(){

            Response response = given().accept(ContentType.JSON).when()
                    .and().queryParam("q","{\"job_id\": \"IT_PROG\"}").get("employees");

            //response.prettyPrint();

            //returns list
           List<String> job_ids = response.path("items.job_id");

            for (String each : job_ids) {

                System.out.println("each = " + each);

                assertEquals("IT_PROG",each);

            }



        }



}
