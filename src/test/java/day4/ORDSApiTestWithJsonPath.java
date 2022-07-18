package day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HRTestBase;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithJsonPath extends HRTestBase {

    @DisplayName("Get request to countries")
    @Test
    public void test1(){

        //"items.findAll {it.region_id==3}.country_name"
        //this sytanx is same both with json and path() method

        Response response = given().accept(ContentType.JSON).when().get("/countries");

        JsonPath jsonPath = response.jsonPath();

        //get all country names where their region id equal to 2
        List<String> countryNameWithId2 = jsonPath.getList("items.findAll {it.region_id==3}.country_name");

        System.out.println("countryNameWithId2 = " + countryNameWithId2);

        //retrieving the 2. name of the country
        System.out.println("jsonPath.get(\"items[1].country_name\") = " + jsonPath.get("items[1].country_name"));

        System.out.println("*****************");

        //get the all elemenst from item[0]
        System.out.println("jsonPath.get(\"items[0]\") = " + jsonPath.get("items[0]"));

        //getting all the country names by storing in a list.

        List<String> list = jsonPath.getList("items.country_id");

        System.out.println("list.size() = " + list.size());

        System.out.println("list = " + list);

        System.out.println("jsonPath.get(\"items.country_name\") = " + jsonPath.get("items.country_name"));
        System.out.println("jsonPath.get(\"items.country_name\") = " + jsonPath.get("items.country_id"));

    }


    @DisplayName("get request / employees with query param")
    @Test
    public void test2(){

        Response response = given().queryParam("limit", 107).when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> emailOfEmployeesWorkingAsItProg = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");

        System.out.println("emailOfEmployeesWorkingAsItProg = " + emailOfEmployeesWorkingAsItProg);

        //get me all the first name of the employees who is making more than 10000
        List<String> makingMoreThan10000 = jsonPath.getList("items.findAll {it.salary>=10000}.first_name");

        System.out.println("makingMoreThan10000 = " + makingMoreThan10000);

        //get the max salary first name
        String maxSalaryFirstName = jsonPath.getString("items.max { it.salary}.first_name");

        System.out.println("maxSalaryFirstName = " + maxSalaryFirstName);

        //get me all the last names of Stevens
        System.out.println("jsonPath.get(\"items.findAll {it.first_name=\\\"Steven\\\"}.last_name\") = " + jsonPath.get("items.findAll {it.first_name==\"Steven\"}.last_name"));

    }




}
