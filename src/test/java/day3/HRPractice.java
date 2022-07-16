package day3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utilities.HRTestBase;

import java.util.List;

public class HRPractice extends HRTestBase {


    @Test
    public void test1(){

        Response response = RestAssured.get("employees");

        System.out.println("response.path(\"items[1]\") = " + response.path("items[1]"));

        //response.prettyPrint();

        /*List<String> names=response.path("first_name");

        for (String name : names) {
            System.out.println("name = " + name);
        }


        System.out.println("response.path(\"first_name[0]\") = " + response.path("first_name[0]"));
        System.out.println("response.path(\"first_name[1]\") = " + response.path("first_name[1]"));
        System.out.println("response.path(\"first_name[2]\") = " + response.path("first_name[2]"));

         */

    }



}
