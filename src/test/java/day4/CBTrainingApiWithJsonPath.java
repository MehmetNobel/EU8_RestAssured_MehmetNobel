package day4;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.CBTrainigTestBase;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class CBTrainingApiWithJsonPath extends CBTrainigTestBase {


    @DisplayName("cybertek training test")
    @Test
    public void test1(){

        Response response = get("student/all");

        JsonPath jsonPath = response.jsonPath();

        jsonPath.prettyPrint();




    }





}
