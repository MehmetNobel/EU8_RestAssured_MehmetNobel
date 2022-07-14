package day2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Practice1 {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://35.171.2.97:1000/ords/hr/";

    }

    @Test
    public void test1(){

        Response response = RestAssured.get("employees");

        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());

        Assertions.assertTrue(response.body().asString().contains("King"));

        System.out.println("response.header(\"Date\") = " + response.header("Date"));


    }



}
