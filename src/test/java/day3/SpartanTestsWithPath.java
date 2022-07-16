package day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.List;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestsWithPath extends SpartanTestBase {


    @DisplayName("Get one spartan with path method")
    @Test
    public void test1() {

        //PATH PARAMETER  ==>> while entering the request
        //response.path()  ==>> key value structure


        Response response = given().accept(ContentType.JSON).and().pathParam("id", 11)
                .when()
                .get("/api/spartans/{id}");

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        response.prettyPrint();

        boolean lorenza = response.body().asString().contains("Lorenza");

        System.out.println("lorenza = " + lorenza);

        assertEquals("application/json", response.header("Content-Type"));

        // we are now retrieving the data from the api by passing key we get the value here.
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());


    }

    @DisplayName("Get all spartans and navigate with path method")
    @Test
    public void test2() {

        //PATH PARAMETER  ==>> while entering the request
        //response.path()  ==>> key value structure


        Response response = given().accept(ContentType.JSON)
                .get("/api/spartans");

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //since json returning us multiple names and ids we can store them in a list.

        List<String> names = response.path("name");
        List<Integer> id = response.path("id");

        //print each name and id one by one
        for (int i = 10; i < 25; i++) {
            System.out.print(names.get(i) + ":");
            System.out.println(id.get(i));


        }

    }
        @DisplayName("Get all spartans and navigate with path method")
        @Test
        public void test3() {

            //PATH PARAMETER  ==>> while entering the request
            //response.path()  ==>> key value structure


            Response response = given().accept(ContentType.JSON)
                    .get("/api/spartans");

            System.out.println("response.getStatusCode() = " + response.getStatusCode());

            //we retrieving the frst id number through the index.
            System.out.println("response.path(\"id[0]\") = " + response.path("id[0]"));

            //retriving 0 . index name
            System.out.println("response.path(\"name[0]\") = " + response.path("name[0]"));

            //retrieve the last index value
            System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));

            //retrieve the 2. last index value
            System.out.println("response.path(\"name[-1]\") = " + response.path("name[-2]"));


        }


}
