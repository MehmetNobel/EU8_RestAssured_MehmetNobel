package day4;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utilities.HRTestBase;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSApiTestWithPath extends HRTestBase {


    @Test
    public void test1(){

        Response response = get("/employees");

        //to retrieve the first item's name.
        System.out.println("response.path(\"first_name[0]\") = " + response.path("items[0].first_name"));


        //response.prettyPrint();

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        System.out.println("response.contentType() = " + response.contentType());

    }






}
