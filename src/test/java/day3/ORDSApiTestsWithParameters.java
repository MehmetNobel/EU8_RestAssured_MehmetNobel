package day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSApiTestsWithParameters {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://35.171.2.97:1000/ords/hr";

    }


    @Test
    public void test1(){

        //we are getting the query result with "q" :
        Response response = given().accept(ContentType.JSON)
                            .and()
                                     .queryParam("q","{\"region_id\": 2}")
                            .get("/countries");

        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        System.out.println("response.body().asString().contains(\"America\") = " + response.body().asString().contains("America"));

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        response.prettyPrint();


    }
    @DisplayName("get all the job=it programmer")
    @Test
    public void test2(){

        //we are getting the query result with "q" :
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .get("/employees");

        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        response.prettyPrint();


    }

    @DisplayName("SALARY:10000")
    @Test
    public void test3(){

        //we are getting the query result with "q" :
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"salary\": 10000}")
                .get("/employees");

        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        response.prettyPrint();


    }











}
